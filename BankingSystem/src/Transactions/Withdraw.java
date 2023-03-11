package Transactions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import AbstractComponents.Closure;

public class Withdraw {
	public static void withdraw() throws SQLException, InterruptedException {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter the Account no.");
			String AccountNumber = sc.nextLine();
			System.out.println("Enter the amount :");
			float amount = sc.nextInt();

			String host = "localhost";
			String port = "3306";
			Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/SeleniumLeraning",
					"root", "outlookftp@12");
			Statement ss = con.createStatement();
			try {
			ResultSet rs = ss.executeQuery(
					"select ab_account_balance from account_balance where ab_Account_number = '" + AccountNumber + "'");
			rs.next();
			float available_bal = Float.parseFloat(rs.getString("ab_account_balance"));

			if (available_bal >= amount) {
				float total_amount = available_bal - amount;
				ss.executeUpdate("update account_balance set ab_account_balance = " + total_amount
						+ "where ab_Account_number = '" + AccountNumber + "'");
				System.out.println("Your total balance is: " + total_amount);
			} else {
				System.out.println("You have insufficient Balance");
				System.out.println("Your total balance is: " + available_bal);
			}
			}catch(SQLException sqlEx) {
				System.out.println("Invalid Account Number");
				Closure.closure();
			}
			Closure.closure();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("Invalid Account Number");
			e.printStackTrace();
			Closure.closure();
		}
	}
}
