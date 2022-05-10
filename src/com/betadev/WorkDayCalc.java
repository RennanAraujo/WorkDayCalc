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
 * todo1: finalizar switch case e consertar bug da diff em DU (1 ou 2)
 */
package com.betadev;

import org.jetbrains.annotations.NotNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.io.IOException;

public class WorkDayCalc {
	public static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT); //static to get accessed //Factory
	public String fileName = "C:\\Code\\MiscJ\\WorkDayCalc\\resources\\AnbimaDatesOnly.txt";
	public HolidaysArrayUploader holidayArrayUploader = new HolidaysArrayUploader();
	public LocalDate[] holidayListInput = holidayArrayUploader.importHolidays(fileName);

	public WorkDayCalc() throws IOException {
	}

	public boolean isHoliday(LocalDate localDateInput) throws IOException {
		boolean result = false;
		for (int i = 0; i < holidayListInput.length; i++) {
			if (holidayListInput[i].equals(localDateInput)) { //yyyy-mm-dd
				result = true;
				break;
			}
		}
		return result;
	}

	public boolean isWeekend(@NotNull LocalDate localDateInput) throws IOException {
		return ((localDateInput.getDayOfWeek().equals(DayOfWeek.SATURDAY)) || localDateInput.getDayOfWeek().equals(DayOfWeek.SUNDAY));
	}

	public int findIndex(LocalDate localDateInput) throws IOException {
		int position = -1;
		for (int j = 0; j < holidayListInput.length; j++) {
			if (holidayListInput[j].equals(localDateInput)) {
				position = j;
				break;
			}
		}
		return position;
	}

	public int holidaysBetween(LocalDate startDate, LocalDate endDate) throws IOException {
		int i = 1;
		int j = 1;
		while (startDate.plusDays(i).isBefore(endDate)) {
			if ((isHoliday(startDate.plusDays(i))) || (isWeekend(startDate.plusDays(i)))) {
				j++;
			}
			i++;
		}
		return i - j + 2; //diff varia entre 1 e 2 dias
	}

	public int daysBetween(LocalDate startDate, LocalDate endDate) {
		int j = 1;
		while (startDate.plusDays(j).isBefore(endDate)) {
			j++;
		}
		return j;
	}

}

