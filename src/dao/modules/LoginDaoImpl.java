package dao.modules;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDaoImpl implements LoginDao{
	
	public LoginDaoImpl() {
		
	}

	@Override
	public boolean passwordValidate(String pwd, String emailAddress) throws Exception{
		
		String passwordReturn = "";
		String emailAdd = "";
		Statement cs = null;
        ResultSet rs = null;
		
		Connection connection = new DBConnection().getDBConnection();
		
		try {
			cs = connection.createStatement();

			if (connection != null) {
				
	            rs = cs.executeQuery("SELECT emailAddress,PASSWORD FROM USER");
				if(rs != null){
					while(rs.next()){
						emailAdd = rs.getString(1);
						passwordReturn = rs.getString(2);
					}
				}
			}
			else{
				throw new Exception("Cannot establish connection with the database");
			}
			
			if(passwordReturn != "" && emailAdd != ""){
				if((passwordReturn.equals(pwd)) && (emailAdd.equals(emailAddress))) 
					return true;
				else
					return false;
			}

		}
		catch(SQLException e){
			throw new Exception("Cannot establish connection with the database");
		}
		finally{
			try {
				if(rs != null)
				rs.close();
				if(cs != null)
				cs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}
		}
		
	
		return false;
		
	}
	
	@Override
	public String getPassword() throws Exception{
		String pwd = null;
		
		Connection connection = new DBConnection().getDBConnection();

		if (connection != null) {
			CallableStatement cs = null;
            ResultSet rs = null;
            cs = connection.prepareCall("SELECT PASSWORD FROM USERDETAILS");
			cs.executeQuery();
			rs = cs.getResultSet();
			if(rs != null){
				while(rs.next()){
					pwd = rs.getString(1);
				}
			}
		}
		else{
			throw new Exception("Cannot establish connection with the database");
		}
		
		return pwd;
		
	}

	@Override
	public void savePassword(String newPassword) throws Exception {
		Connection connection = new DBConnection().getDBConnection();
		CallableStatement cs = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
		if (connection != null) {
			try {
			
			connection.setAutoCommit(false);
			
			
			ps = connection.prepareStatement("UPDATE USERDETAILS SET PASSWORD = ?");
			ps.setString(1, newPassword);
			ps.executeUpdate();
			connection.commit();
			
			
			}
			 catch (SQLException e) {
					// TODO Auto-generated catch block
					try {
						connection.rollback();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						throw new Exception("Error committing the save statement");
					}
					throw new Exception("Error committing the save statement");
					
				}
				finally{
					try {
						if(rs != null)
						rs.close();
						if(cs != null)
						cs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
					}
					
				}
			
		}
		else{
			throw new Exception("Cannot establish connection with the database");
		}
		
	}

	@Override
	public String getEmailAddress() throws Exception {
		String emailaddr = null;
		
		Connection connection = new DBConnection().getDBConnection();

		if (connection != null) {
			CallableStatement cs = null;
            ResultSet rs = null;
            cs = connection.prepareCall("SELECT EMAILADDRESS FROM USERDETAILS");
			cs.executeQuery();
			rs = cs.getResultSet();
			if(rs != null){
				while(rs.next()){
					emailaddr = rs.getString(1);
				}
			}
		}
		else{
			throw new Exception("Cannot establish connection with the database");
		}
		
		return emailaddr;

	}

	@Override
	public void saveEmailAddress(String emailAddr) throws Exception {
		
		Connection connection = new DBConnection().getDBConnection();
		CallableStatement cs = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
		if (connection != null) {
			try {
			
			connection.setAutoCommit(false);
			
			
			ps = connection.prepareStatement("UPDATE USERDETAILS SET EMAILADDRESS = ?");
			ps.setString(1, emailAddr);
			ps.executeUpdate();
			connection.commit();
			
			
			}
			 catch (SQLException e) {
					// TODO Auto-generated catch block
					try {
						connection.rollback();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						throw new Exception("Error committing the save statement");
					}
					throw new Exception("Error committing the save statement");
					
				}
				finally{
					try {
						if(rs != null)
						rs.close();
						if(cs != null)
						cs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
					}
					
				}
			
		}
		else{
			throw new Exception("Cannot establish connection with the database");
		}
	}

}
