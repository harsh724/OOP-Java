package BankDetails;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import AbstractComponents.Closure;
import CreationDeletion.CreateAccount;
import Transactions.CheckBalance;
import Transactions.Deposit;
import Transactions.Withdraw;

public class BankDetailsName extends CreateAccount {

	public static void main(String[] args) throws SQLException, InterruptedException {
		choice();
	}

	public static void choice() throws SQLException, InterruptedException {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Please select your choice");
			System.out.println("PRESS 1 for Open an account in SBI");
			System.out.println("PRESS 2 to check balance");
			System.out.println("PRESS 3 to withdraw from saving account");
			System.out.println("PRESS 4 to deposit money in saving account");
			try {
			int choice = sc.nextInt();
			if (choice == 1)
				createAccount();
			if (choice == 4)
				Deposit.deposit();
			if(choice == 3)
				Withdraw.withdraw();
			if(choice == 2)
				CheckBalance.availableBalance();
			}catch(InputMismatchException ex) {
				System.out.println("Enter valid Input");
				Closure.closure();
			}
		}
	}

}
