package com.betadev;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class HolidaysArrayUploader {
    private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT); //utilizado no método e no main
    public LocalDate[] importHolidays(String fileName) throws IOException { //array de LocalDate, só coloquei essa exception pq foi pedido, não entendi
        String output = readFile(fileName); //tem que ler linha a linha e fazer o parse individualmente
        String[] holydayList = output.split("\\r?\\n"); //array de string que precisa ser parseado para data
        LocalDate[] holydayListFmt = new LocalDate[holydayList.length]; //se for nulo, dá nullPointException
        //não consegui parsear o array inteiro, vou usar um laço for
        for (int j=0; j<holydayList.length; j++) { //daria pra transformar isso num método dentro do método?
            holydayListFmt[j] = LocalDate.parse(holydayList[j], fmt);
        }
        return holydayListFmt; //retorna a lista de datas no formato yyyy-MM-dd
    }
    private String readFile(String filename) throws IOException {
        String content = null;
        File file = new File(filename); // For example, foo.txt
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader != null){
                reader.close();
            }
        }
        //System.out.println("Anbima holidays successfully imported");
        return content;
    }
}
