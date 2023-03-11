package Transactions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import AbstractComponents.Closure;

public class CheckBalance {
	public static void checkBalance(String AccountNumber) throws SQLException {
		String host = "localhost";
		String port = "3306";
		Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/SeleniumLeraning",
				"root", "<Password>");
		Statement ss = con.createStatement();
		ResultSet rs = ss.executeQuery(
				"select ab_account_balance from account_balance where ab_Account_number = '" + AccountNumber + "'");
		rs.next();
		System.out.println("Your total Balance is: "+rs.getString("ab_account_balance"));
	}
	public static void availableBalance() throws SQLException, InterruptedException {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter your account Number");
			String accNo = sc.next();
			checkBalance(accNo);
		}
		catch(SQLException sqlEx) {
			System.out.println("Invalid Account Number");
			Closure.closure();
		}
	}

}
