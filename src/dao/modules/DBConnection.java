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
			 
			connection = DriverManager.getConnection(
					"jdbc:sqlite:C:/python/dartdb.db");
 
		} catch (SQLException e) {
 
			throw e;
 
		}
		return connection;
	}

}
