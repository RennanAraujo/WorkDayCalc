/**
 * @author: Rennan Araujo
 * v0.0.1 - primeira implementação
 * v0.0.2 - segregação dos métodos
 * v0.0.3 - alteração no formato do arquivo de feriados de dd/mm/yyyy para yyyy-mm-dd
 * v0.0.4 - bug no método isHolyday está retornando false para feriados inseridos
 * v0.0.4 - inclusão de método para retornar a posição de uma data caso esteja no array de feriados
 * v0.0.5 - fix no bug do isholyday
 * v0.0.6 - passando importHolydays para AppRead.java
 * v0.0.7 - migração para o projeto WorkDayCalc com alteração no nome dos arquivos
 * v0.0.8 - iniciado o versionamento via GitHub
 * v0.0.9 - retirados comentários desnecessários
 * v0.0.10 - retirado parâmetro de feriados dos métodos isHoliday e findIndex
 * todo: ainda não está reconhecendo os finais de semana
 */
package com.betadev;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.io.IOException;
import java.util.Scanner;
//import java.lang.*;


public class WorkDayCalc {
        public static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT); //static to get accessed
        public boolean isHoliday(LocalDate localDateInput) throws IOException {
            String fileName = "C:\\Code\\MiscJ\\WorkDayCalc\\resources\\AnbimaDatesOnly.txt";
            HolidaysArrayUploader holidayArrayUploader = new HolidaysArrayUploader();
            LocalDate[] holidayListInput = holidayArrayUploader.importHolidays(fileName);
            boolean result = false;
            for (int i = 0; i < holidayListInput.length; i++) {
                if(holidayListInput[i].equals(localDateInput)) { //yyyy-mm-dd
                    result = true;
                    break;
                }
            }
            return result; //boa prática: somente 1 retorno
        }
        public int findIndex(LocalDate localDateInput) throws IOException {
            String fileName = "C:\\Code\\MiscJ\\WorkDayCalc\\resources\\AnbimaDatesOnly.txt";
            HolidaysArrayUploader holidayArrayUploader = new HolidaysArrayUploader();
            LocalDate[] holidayListInput = holidayArrayUploader.importHolidays(fileName);
            int position = -1;
            for (int i = 0; i < holidayListInput.length; i++) {
                if(holidayListInput[i].equals(localDateInput)) {
                    position = i;
                    break;
                }
            }
            return position;
        }
        public static void main(String[] args) throws IOException { //o main é para a execução, não há regras de negócio aqui
            WorkDayCalc workDayCalc = new WorkDayCalc(); //declarando a variável para acessar o método não-estático isHoliday
            Scanner sc = new Scanner(System.in);
            System.out.println("=====THIS APP CHECKS IF A DATE IS HOLIDAY=====");
            System.out.print("Enter a date to test (use dd/MM/yyyy): ");
            LocalDate localDateToTest = LocalDate.parse(sc.nextLine(), fmt);
            boolean check = workDayCalc.isHoliday(localDateToTest);
            int index = workDayCalc.findIndex(localDateToTest);
            System.out.println("Is a this date a holiday? " + check);
            System.out.println("Position in holiday array (-1 if not found): " + index);
        }
    }

