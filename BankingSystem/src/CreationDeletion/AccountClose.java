package CreationDeletion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import AbstractComponents.Closure;

public class AccountClose {

	public static void accountClose() throws InterruptedException, SQLException {
		String user = "localhost";
		String port = "3306";
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://" + user + ":" + port + "/SeleniumLeraning",
					"root", "outlookftp@12");
			Statement s = con.createStatement();
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the AccountNumber");
			String accNo = sc.next();
			ResultSet rs = s.executeQuery("Select * from bankdetails where account_number = '" + accNo + "'");
			System.out.println("Enter the Name");
			String name = sc.next();
			System.out.println("Please state the reason why you want to close the account");
			String reason = sc.next();
			System.out.println("Please wait ");
			int i = 0;
			while (i < 5) {
				Thread.sleep(500);
				System.out.print(".");
				i++;
			}
			s.executeUpdate("delete from bankdetails where Name ='" + name + "' and account_number = '" + accNo + "' ");
			System.out.println();
			System.out.println(
					"Account Deletion Request Added Sucessfully.After verification your account will be deleted within 4 business days. "
					+ "Collect your remaining balance from your nearest branch within 14 business days");
			Closure.closure();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Invalid!!");
			Closure.closure();
			e.printStackTrace();
		}

	}

}
