package com.betadev;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class HolidaysArrayUploader {
    private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
    public LocalDate[] importHolidays(String fileName) throws IOException {
        String output = readFile(fileName); //output de seq de caracteres
        String[] holidayList = output.split("\\r?\\n"); //array de string que precisa ser parseado para data
        LocalDate[] holidayListFmt = new LocalDate[holidayList.length];
        for (int j=0; j<holidayList.length; j++) {
            holidayListFmt[j] = LocalDate.parse(holidayList[j], fmt);
        }
        return holidayListFmt; //yyyy-MM-dd
    }
    private String readFile(String filename) throws IOException {
        String content = null;
        File file = new File(filename);
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        } finally {
            if(reader != null){
                reader.close();
            }
        }
        //System.out.println("Anbima holidays successfully imported");
        return content;
    }
}
