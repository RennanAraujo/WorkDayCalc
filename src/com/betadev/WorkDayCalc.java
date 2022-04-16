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
 * v0.0.8 - iniciado o versionamento via GIT
 * todo: retirar parâmetro de array
 */
package com.betadev;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.io.IOException;
import java.util.Scanner;
import java.lang.*;


public class WorkDayCalc {
        public static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT); //static to get accessed
        //private static DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE; //.withResolverStyle(ResolverStyle.STRICT);
        //TODO: retirar o input dos feriados como parâmetro >> deixar no HolidaysArrayUploader
        public boolean isHoliday(LocalDate localDateInput, LocalDate[] holidayListInput) {
            boolean result = false;
            //deveria usar um for melhorado? somente se o índice não fosse relevante, o que é o caso
            for (int i = 0; i < holidayListInput.length; i++) {
                //possível problema no parse
                //devo usar isEqual ou equals >> ambos retornam false indevidamente
                if(holidayListInput[i].equals(localDateInput)) { //yyyy-mm-dd
                    //System.out.println("comparando " + holydayListInput[i] + " " + localDateInput);
                    result = true;
                    break;
                }
            }
            return result; //boa prática: somente 1 retorno
        }
        public int findIndex(LocalDate localDateInput, LocalDate[] holidayListInput) {
            int position = -1;
            for (int i = 0; i < holidayListInput.length; i++) {
                if(holidayListInput[i].equals(localDateInput)) { //parse retirado após inserir uma lista já formatada
                    position = i;
                    break;
                }
            }
            return position;
        }
        public static void main(String[] args) throws IOException { //o main é para a execução, não há regras de negócio aqui
            WorkDayCalc workDayCalc = new WorkDayCalc(); //declarando a variável para acessar o método não-estático isHolyday
            HolidaysArrayUploader holidayArrayUploader = new HolidaysArrayUploader();
            Scanner sc = new Scanner(System.in);
            System.out.println("=====THIS APP CHECKS IF A DATE IS HOLIDAY=====");
            System.out.print("Enter a date to test (use dd/MM/yyyy): ");
            LocalDate localDateToTest = LocalDate.parse(sc.nextLine(), fmt);//LocalDate no fmt yyyy-MM-dd
            String fileName = "C:\\Code\\MiscJ\\WorkDayCalc\\resources\\AnbimaDatesOnly.txt";
            LocalDate[] holidaysArray = holidayArrayUploader.importHolidays(fileName);//alterei o fmt pra não precisar parsear
            boolean check = workDayCalc.isHoliday(localDateToTest, holidaysArray);
            int index = workDayCalc.findIndex(localDateToTest, holidaysArray);
            System.out.println("Is a this date a holiday? " + check); //está retornando falso, mesmo estando na lista
            System.out.println("Position in holiday array: " + index);//retorna a posição do feriado, se não -1 caso não encontre
        }
    }

