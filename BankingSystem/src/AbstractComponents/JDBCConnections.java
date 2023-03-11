package AbstractComponents;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnections {
	public void jdbcConnections() throws SQLException {
		String host = "localhost";
		String port = "3306";
		Connection con = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/SeleniumLeraning", "root", "<Password>");
		
	}

}
