package AbstractComponents;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Authenticator {	
	public static Boolean authenticator(String Accno, String pin) throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SeleniumLeraning", "root", "outlookftp@12");
		String Query = "Select * from bankdetails where account_number = '"+Accno+"'";
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(Query);
		while(rs.next()) {
			if(rs.getString("uniquepin").equals(pin)) {
				return true;
			}
			//return false;
		}
		return false;
		
	}
	

}
