package dao.modules;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public Connection getDBConnection() throws Exception{
		try {
			Class.forName("org.sqlite.JDBC"); 
		} catch (ClassNotFoundException e) {
			throw e;
		}

		Connection connection = null;
		try {
			
			String connectionString =  "jdbc:sqlite:C:\\dartdb\\dartdb.db"; 
			connection = DriverManager.getConnection(connectionString);
 
		} catch (SQLException e) {
 
			throw e;
 
		}
		return connection;
	}

}
