package AbstractComponents;

import java.sql.SQLException;
import java.util.Scanner;

import BankDetails.BankDetailsName;

public class Closure {
	public static void closure() throws SQLException, InterruptedException {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("PRESS 1 FOR EXIT PRESS 2 FOR MAIN MENU");
			int choice = sc.nextInt();
			if (choice == 1) {
				System.out.println("THANK YOU FOR BANKING WITH SBI");
			} else
				BankDetailsName.choice();
		}
	}

}
