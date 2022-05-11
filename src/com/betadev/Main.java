package com.betadev;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

import static com.betadev.WorkDayCalc.fmt;

public class Main {
	public static void main(String[] args) throws IOException {
		WorkDayCalc workDayCalc = new WorkDayCalc();
		Scanner sc = new Scanner(System.in);
		System.out.println("=====CHOOSE OPTION (use dd/MM/yyyy)=====");
		System.out.println("1. Check if date is holiday\n" + "2. Period\n" + "3. Elapsed time working days\n");
		int choose = sc.nextInt();
		sc.nextLine(); //read the '\n'
		switch (choose) {
			case 1: {
				System.out.print("Enter a date to test: ");
				String input = sc.nextLine();
				LocalDate dateToTest = LocalDate.parse(input, fmt);
				boolean checkHD = workDayCalc.isHoliday(dateToTest);
				System.out.println("Is this date a holiday? " + checkHD);
				break;
			}
			case 2:
				System.out.print("Enter start date: ");
				LocalDate startDate = LocalDate.parse(sc.nextLine(), fmt);
				System.out.println("Enter end date: ");
				LocalDate endDate = LocalDate.parse(sc.nextLine(), fmt);
				Period period = Period.between(startDate, endDate);
				System.out.println("Elapsed time: " + period.getYears() + " years "
						+ period.getMonths() + " months " + period.getDays() + " days");
				break;
			case 3:
				System.out.print("Enter start date: ");
				startDate = LocalDate.parse(sc.nextLine(), fmt);
				System.out.print("Enter end date: ");
				endDate = LocalDate.parse(sc.nextLine(), fmt);
				if (endDate.isAfter(startDate)) {
					System.out.println("Elapsed time in working days: "
							+ workDayCalc.holidaysBetween(startDate, endDate));
				} else {
					System.out.println("End date is before start date"); //essa regra deveria estar aqui ou no método?
				}
				break;
		}
		sc.close();
	}
}
