package data;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {

	/**
	 * create connection
	 * 
	 * @author viettuts.vn
	 * @param dbURL:    database's url
	 * @param userName: username is used to login
	 * @param password: password is used to login
	 * @return connection
	 */
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String Url = "jdbc:sqlserver://localhost:1433;databaseName=QLTV;user=sa;password=sa";
			conn = DriverManager.getConnection(Url);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return conn;

	}

}