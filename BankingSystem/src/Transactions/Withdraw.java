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

public class Withdraw {
	public static void withdraw() throws SQLException, InterruptedException {
		try (Scanner sc = new Scanner(System.in)) {
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
				try {
					ResultSet rs = ss
							.executeQuery("select ab_account_balance from account_balance where ab_Account_number = '"
									+ AccountNumber + "'");
					rs.next();
					float available_bal = Float.parseFloat(rs.getString("ab_account_balance"));

					if (available_bal >= amount) {
						float total_amount = available_bal - amount;
						ss.executeUpdate("update account_balance set ab_account_balance = " + total_amount
								+ "where ab_Account_number = '" + AccountNumber + "'");
						System.out.println("Your total balance is: " + total_amount);
						String Query = "insert into TH_TRSN_HSTR(TH_ACC_NO, TH_CR_DR_IND, TH_AMNT, TH_LST_UPDTE) values (?, 0, ?, CURRENT_TIMESTAMP)";
						PreparedStatement ps = con.prepareStatement(Query);
						ps.setString(1, AccountNumber);
						ps.setFloat(2, amount);
						ps.execute();
					} else {
						System.out.println("You have insufficient Balance");
						System.out.println("Your total balance is: " + available_bal);
					}
				} catch (SQLException sqlEx) {
					System.out.println("Invalid Account Number");
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("Invalid Account Number");
			e.printStackTrace();
		} finally {
			//Closure.closure();
		}
	}
}
