/**
 * @author: Rennan Araujo
 * v0.0.1 - primeira implementação
 * v0.0.2 - segregação dos métodos
 * v0.0.3 - alteração no formato do arquivo de feriados de dd/mm/yyyy para yyyy-mm-dd
 * v0.0.4 - bug no método isHoliday está retornando false para feriados inseridos
 * v0.0.4 - inclusão de método para retornar a posição de uma data caso esteja no array de feriados
 * v0.0.5 - fix no bug do isholiday
 * v0.0.6 - passando importHolidays para AppRead.java
 * v0.0.7 - migração para o projeto WorkDayCalc com alteração no nome dos arquivos
 * v0.0.8 - iniciado o versionamento via GitHub
 * v0.0.9 - retirados comentários desnecessários
 * v0.0.10 - retirado parâmetro de feriados dos métodos isHoliday e findIndex
 * v0.0.11 - retirado importação de arquivo dos métodos isHoliday e findIndex
 * v0.1.0 - criada classe Main para executar o programa
 * v0.1.1 - incluidos métodos isWeekend, holidaysBetween and daysBetween
 * v0.1.2 - switch case
 * v0.1.3 - Add método holidaysBetween
 * v0.1.4 - Refactor of constructor WorkDayCalc and add of numberOfNonWorkingDaysBetween and nonWorkingDaysBetweenShort methods
 * todo1: finalizar switch case e consertar bug da diff em DU (1 ou 2)
 */
package com.betadev;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class WorkDayCalc {
	public static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT); //static to get accessed //Factory
	public final String FILENAME = "C:\\Code\\MiscJ\\WorkDayCalc\\resources\\AnbimaDatesOnly.txt";
	public LocalDate[] holidayList = null;

	public WorkDayCalc() throws IOException { //Constructor padrão não lança exceção
		//verificar try catch para erro
		//não fazer chamada potencialmente perigosa fora daqui e sim dentro de um método factory ou um construtor
		HolidaysArrayUploader holidayArrayUploader = new HolidaysArrayUploader();
		holidayList = holidayArrayUploader.importHolidays(FILENAME);//inicialização
	}

	public boolean isHoliday(LocalDate localDateInput) {
		boolean result = false;
		for (int i = 0; i < holidayList.length; i++) {
			if (holidayList[i].equals(localDateInput)) { //yyyy-mm-dd
				result = true;
				break;
			}
		}
		return result;
	}

	public boolean isWeekend(LocalDate localDateInput) {
		return ((localDateInput.getDayOfWeek().equals(DayOfWeek.SATURDAY)) || localDateInput.getDayOfWeek().equals(DayOfWeek.SUNDAY));
	}

	public int findIndex(LocalDate localDateInput) {
		int position = -1;
		for (int j = 0; j < holidayList.length; j++) {
			if (holidayList[j].equals(localDateInput)) {
				position = j;
				break;
			}
		}
		return position;
	}

	public Period getPeriod(LocalDate startDate, LocalDate endDate) {
		return Period.between(startDate, endDate);

	}

	public long daysBetween(LocalDate startDate, LocalDate endDate) {
		return ChronoUnit.DAYS.between(startDate, endDate);
	}

	public LocalDate[] addDate(LocalDate[] arr, LocalDate x) {
		// create a new ArrayList
		List<LocalDate> arrayList = new ArrayList<>(Arrays.asList(arr));
		// Add the new element
		if (x != null) {
			arrayList.add(x);
			//arrayList.removeAll(Collections.singletonList(null));
			//removeNullUsingIterator(arrayList);
			//https://acervolima.com/programa-java-para-remover-nulos-de-um-conteiner-de-lista/
		}
		while (arrayList.remove(null)) {
		}
		// Convert the Arraylist to array
		arr = arrayList.toArray(arr);
		return arr;
	}
	//public LocalDate onlyDates (LocalDate[] arr, LocalDate startDate, LocalDate endDate){
		//int shortLen = nonWorkingDaysBetween(startDate, endDate).n;
	//}
	public LocalDate[] nonWorkingDaysBetween(LocalDate startDate, LocalDate endDate) {
		int j;
		int n;
		long len = daysBetween(startDate, endDate);
		LocalDate[] arr = new LocalDate[(int) len];
		//LocalDate[] arr = new LocalDate[0];
		for (j = 0; j <= len; j++) { //<= pra incluir o último dia
			if (isHoliday(startDate.plusDays(j)) || isWeekend(startDate.plusDays(j))) {
				arr = addDate(arr, startDate.plusDays(j));
				//System.out.println("adding to list: " + startDate.plusDays(j));
			}
		}
		n = j;
		//arr = LocalDate.parse(arr, fmt);
		return arr;
	}

	public int numberOfNonWorkingDaysBetween(LocalDate startDate, LocalDate endDate) {
		int j;
		int count = 0;
		int len = nonWorkingDaysBetween(startDate, endDate).length;
		for (j = 0; j <= len; j++) { //<= pra incluir o último dia
			if (isHoliday(startDate.plusDays(j)) || isWeekend(startDate.plusDays(j))) {
				count++;
			}
		}
		return count;
	}
	public LocalDate[] nonWorkingDaysBetweenShort(LocalDate startDate, LocalDate endDate){
		LocalDate[] arr = nonWorkingDaysBetween(startDate, endDate);
		int shortLen = numberOfNonWorkingDaysBetween(startDate, endDate);
		LocalDate[] shortArr = new LocalDate[shortLen];
		int i;
		for(i=0; i< arr.length; i++){
			if(arr[i] != null){
				shortArr = addDate(shortArr, arr[i]);
			}
		}
	return shortArr;
	}
}


