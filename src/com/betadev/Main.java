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
		System.out.println("=====CHOOSE OPTION (use dd/MM/yyyy)=====");
		System.out.println("1. Check if date is holiday\n"
				+ "2. Period\n"
				+ "3. Elapsed time in days\n"
				+ "4. Non working days between dates\n"
				+ "5. Number of non working days between dates\n"
				+ "6. Number of working days between dates\n");
		int choose = sc.nextInt();
		sc.nextLine(); //read the '\n'
		switch (choose) {
			case 1: {
				System.out.print("Enter a date: ");
				LocalDate dateToTest = LocalDate.parse(sc.nextLine(), fmt);
				boolean checkHD = workDayCalc.isHoliday(dateToTest);
				System.out.println("Is this date a holiday? " + checkHD);
				break;
			}
			case 2:
				System.out.print("Enter start date: ");
				LocalDate startDate = LocalDate.parse(sc.nextLine(), fmt); //definido aqui
				System.out.print("Enter end date: ");
				LocalDate endDate = LocalDate.parse(sc.nextLine(), fmt);
				Period period = workDayCalc.getPeriod(startDate, endDate); //a lógica fica na classe original
				System.out.println("Elapsed time: " + period.getYears() + " years "
						+ period.getMonths() + " months " + period.getDays() + " days");
				break;
			case 3:
				System.out.print("Enter start date: ");
				startDate = LocalDate.parse(sc.nextLine(), fmt);
				System.out.print("Enter end date: ");
				endDate = LocalDate.parse(sc.nextLine(), fmt);
				if (endDate.isAfter(startDate)) {
					System.out.println("Elapsed time in days: "
							+ workDayCalc.numberOfDaysBetween(startDate, endDate));
				} else {
					System.out.println("End date is before start date"); //essa regra deveria estar aqui ou no método?
				}
				break;
			case 4:
				System.out.print("Enter date: ");
				startDate = LocalDate.parse(sc.nextLine(), fmt);
				System.out.print("Enter end date: ");
				endDate = LocalDate.parse(sc.nextLine(), fmt);
				LocalDate[] arr = workDayCalc.nonWorkingDaysBetween(startDate, endDate);
				//LocalDate[] arrFull = workDayCalc.nonWorkingDaysBetween(startDate, endDate);
				//System.out.println("Short " + arr.length + "\nFull " + arrFull.length);
				System.out.println(Arrays.toString(arr));
				//System.out.println(Arrays.toString(arrFull));
				break;
			case 5:
				System.out.print("Enter date: ");
				startDate = LocalDate.parse(sc.nextLine(), fmt);
				System.out.print("Enter end date: ");
				endDate = LocalDate.parse(sc.nextLine(), fmt);
				int result5 = workDayCalc.numberOfNonWorkingDaysBetween(startDate, endDate);
				System.out.println("Number of non working days between: " + result5);
				break;
			case 6:
				System.out.print("Enter date: ");
				startDate = LocalDate.parse(sc.nextLine(), fmt);
				System.out.print("Enter end date: ");
				endDate = LocalDate.parse(sc.nextLine(), fmt);
				int result6 = workDayCalc.workingDaysBetween(startDate, endDate);
				System.out.println("Number of working days between: " + result6);
		}
		sc.close();
	}
}
