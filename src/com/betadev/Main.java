package com.betadev;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Scanner;

import static com.betadev.WorkDayCalc.fmt;

public class Main {
	public static void main(String[] args) throws IOException {
		WorkDayCalc workDayCalc = new WorkDayCalc();
		Scanner sc = new Scanner(System.in);
		System.out.println("=====THIS APP CHECKS IF A DATE IS HOLIDAY=====");
		//System.out.print("Enter a date to test (use dd/MM/yyyy): ");
		//LocalDate dateToTest = LocalDate.parse(sc.nextLine(), fmt);
		System.out.print("Enter start date: ");
		LocalDate startDate = LocalDate.parse(sc.nextLine(), fmt);
		System.out.print("Enter end date: ");
		LocalDate endDate = LocalDate.parse(sc.nextLine(), fmt);
		/*
		boolean checkHD = workDayCalc.isHoliday(dateToTest);
		boolean checkWE = workDayCalc.isWeekend(dateToTest);
		*/
		//int index = workDayCalc.findIndex(dateToTest);
		Period period = Period.between(startDate, endDate);
		/*
		 System.out.println("Is this date a holiday? " + checkHD);
		 System.out.println("Is this date in a weekend? " + checkWE);
		*/
		System.out.println("Elapsed time: " + period.getYears() + " years " + period.getMonths() + " months " + period.getDays() + " days");
		//System.out.println("xxx: " + workDayCalc.workDaysBetween(startDate, endDate));
		System.out.println("Elapsed time in days: " + workDayCalc.daysBetween(startDate, endDate));
		System.out.println("Elapsed time in working days: " + workDayCalc.holidaysBetween(startDate, endDate));
		//System.out.println("Position in holiday array (-1 if not found): " + index);
	}
}
