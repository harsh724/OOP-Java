package CreationDeletion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import AbstractComponents.Closure;

public class CreateAccount {
	Scanner sc = new Scanner(System.in);
	String host = "localhost";
	String port = "3306";

	public static void createAccount() throws SQLException, InterruptedException {
		try (Scanner sc = new Scanner(System.in)) {
			String host = "localhost";
			String port = "3306";
			Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/SeleniumLeraning", "root",
					"<Password>");

			System.out.println("Enter your Name");
			String Name = sc.next();
			System.out.println("Enter your Adress");
			String Address = sc.next();
			Thread.sleep(1000);
			System.out.println("Enter your Gender");
			String Sex = sc.next();
			System.out.println("Enter your PhoneNo.");
			String PhoneNo = sc.next();
			System.out.println("Enter your EmailID");
			String emailID = sc.next();
			System.out.println("Enter your PAN");
			String PAN = sc.next();

			String uniquecode = generatePin();
			String Query1 = " insert into bankdetails (Name, Address, sex, phoneno, emailid, PAN, uniquepin, account_number)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
			Statement ss = con.createStatement();
			ResultSet rs = ss.executeQuery("select count(*) from bankdetails");
			int acc_no = 0;
			try (PreparedStatement s = con.prepareStatement(Query1);) {
				s.setString(1, Name);
				s.setString(2, Address);
				s.setString(3, Sex);
				s.setString(4, PhoneNo);
				s.setString(5, emailID);
				s.setString(6, PAN);
				s.setString(7, uniquecode);
				s.setInt(8, 0);
				s.execute();
				// con.close();
				while (rs.next()) {
					acc_no = 11001 + Integer.parseInt(rs.getString("count(*)"));
				}
				// s.setInt(8, acc_no);
				ss.executeUpdate(
						"update bankdetails set account_number = '" + acc_no + "'" + " where Name = '" + Name + "'");
				// s.execute();
			} catch (SQLException sqle) {
				System.out.println("Exception Caught");
				sqle.printStackTrace();
			}
			String Query2 = "insert into account_balance (ab_Name, ab_Account_number, ab_account_balance) values (?, ?, ?)";
			try (PreparedStatement s = con.prepareStatement(Query2);) {
				s.setString(1, Name);
				s.setInt(2, acc_no);
				s.setFloat(3, 0);
				s.execute();
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Your account has been created");
		Closure.closure();

	}

	public static String generatePin() {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Generate 4 digit pin");
			String uniquecode = sc.next();
			System.out.println("Confirm your pin");
			String uniquecodeConfirm = sc.next();
			if (!uniquecode.equals(uniquecodeConfirm)) {
				System.out.println("either your pin doest match with original or you entered more than 4 digit");
				return generatePin();
			} else
				return uniquecode;
		}

	}
}
