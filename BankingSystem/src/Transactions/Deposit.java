package Transactions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import AbstractComponents.Authenticator;
import AbstractComponents.Closure;

public class Deposit {
	public static void deposit() throws SQLException, InterruptedException {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter the Account no.");
			String AccountNumber = sc.nextLine();
			System.out.println("enter your unique pin");
			String pin = sc.nextLine();
			if (!Authenticator.authenticator(AccountNumber, pin)) {
				System.out.println("Your pin is invalid! Please try again");
				Closure.closure();
			} else {
				System.out.println("Enter the amount :");
				float amount = sc.nextInt();

				String host = "localhost";
				String port = "3306";
				Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/SeleniumLeraning",
						"root", "outlookftp@12");
				Statement ss = con.createStatement();
				ResultSet rs = null;
				try {
					rs = ss.executeQuery("select ab_account_balance from account_balance where ab_Account_number = '"
							+ AccountNumber + "'");

					rs.next();
					float total_amount = amount + Float.parseFloat(rs.getString("ab_account_balance"));

					ss.executeUpdate("update account_balance set ab_account_balance = " + total_amount
							+ "where ab_Account_number = '" + AccountNumber + "'");
					System.out.println("Your total balance is: " + total_amount);
					String Query = "insert into TH_TRSN_HSTR(TH_ACC_NO, TH_CR_DR_IND, TH_AMNT, TH_LST_UPDTE) values (?, 1, ?, CURRENT_TIMESTAMP)";
					PreparedStatement ps = con.prepareStatement(Query);
					ps.setString(1, AccountNumber);
					ps.setFloat(2, amount);
					ps.execute();
				} catch (SQLException sqlEx) {
					System.out.println("Invalid Account Number");
					sqlEx.getCause();
				}
				Closure.closure();
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("Invalid Account Number");
			Closure.closure();
			e.printStackTrace();
		}

	}
}

