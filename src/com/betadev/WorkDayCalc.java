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
 * v0.1.5 - Add method workingDaysBetween
 * v0.1.4 - Remove method nonWorkingDaysBetweenShort and refactor numberOfNonWorkingDaysBetween
 * v0.1.5 - Refactor in workingDaysBetween to match results of NETWORKDAYS from MS Excel
 * todo1: como implementar testes? ver framework JUnit | fórmula bate com NETWORKDAYS
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

	public WorkDayCalc() { //Add o try catch e não precisa mais Throw IOException
		try {
			//Chamada potencialmente perigosa: precisa ser feita dentro de um método factory ou um construtor
			HolidaysArrayUploader holidayArrayUploader = new HolidaysArrayUploader();
			holidayList = holidayArrayUploader.importHolidays(FILENAME);//inicialização
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public long numberOfDaysBetween(LocalDate startDate, LocalDate endDate) {
		return ChronoUnit.DAYS.between(startDate, endDate); //fazer cast para inteiro onde for usar
	}

	public LocalDate[] addDate(LocalDate[] arr, LocalDate x) {
		// create a new ArrayList
		List<LocalDate> arrayList = new ArrayList<>(Arrays.asList(arr));
		// Add the new element
		if (x != null) {
			arrayList.add(x);
		}
		arr = arrayList.toArray(arr);
		return arr;
	}

	public LocalDate[] nonWorkingDaysBetween(LocalDate startDate, LocalDate endDate) {
		int j;
		long len = numberOfDaysBetween(startDate, endDate);
		//LocalDate[] arr = new LocalDate[(int) len];
		LocalDate[] arr = new LocalDate[0];
		for (j = 0; j <= len; j++) { //<= pra incluir o último dia
			if (isHoliday(startDate.plusDays(j)) || isWeekend(startDate.plusDays(j))) {
				arr = addDate(arr, startDate.plusDays(j)); //tá add os elementos ao
				//System.out.println("adding to list: " + startDate.plusDays(j));
			}
		}
		return arr;
	}

	public int numberOfNonWorkingDaysBetween(LocalDate startDate, LocalDate endDate){
		return nonWorkingDaysBetween(startDate, endDate).length;
	}

	public int workingDaysBetween(LocalDate startDate, LocalDate endDate){
		return (int) (numberOfDaysBetween(startDate, endDate) - numberOfNonWorkingDaysBetween(startDate, endDate)+1); //data inicial e final inclusive
	}
}


