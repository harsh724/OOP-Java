package Transactions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import AbstractComponents.Closure;

public class Deposit {
	public static void deposit() throws SQLException, InterruptedException {
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
			ResultSet rs = null;
			try {
			rs = ss.executeQuery(
					"select ab_account_balance from account_balance where ab_Account_number = '" + AccountNumber + "'");
			
			rs.next();
			float total_amount = amount + Float.parseFloat(rs.getString("ab_account_balance"));
			
			ss.executeUpdate("update account_balance set ab_account_balance = " + total_amount
					+ "where ab_Account_number = '" + AccountNumber + "'");
			System.out.println("Your total balance is: " + total_amount);
			}catch(SQLException sqlEx) {
				System.out.println("Invalid Account Number");
				Closure.closure();
			}
			Closure.closure();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("Invalid Account Number");
			Closure.closure();
			e.printStackTrace();
		}

	}
}
