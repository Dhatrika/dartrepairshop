package dao.modules;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.util.DartConstants;

public class LoginDaoImpl implements LoginDao{
	
	public LoginDaoImpl() {
		
	}

	@Override
	public String passwordValidate(String pwd, String emailAddress) throws Exception{
		
		String passwordReturn = "";
		String emailAdd = "";
		int isOwner = 0;
		Statement cs = null;
        ResultSet rs = null;
		
		Connection connection = new DBConnection().getDBConnection();
		
		try {
			cs = connection.createStatement();

			if (connection != null) {
				
	            rs = cs.executeQuery("SELECT emailAddress,password,isowner FROM USER WHERE emailAddress = '" + emailAddress + "' AND password = '" + pwd + "'");
				if(rs != null){
					while(rs.next()){
						emailAdd = rs.getString(1);
						passwordReturn = rs.getString(2);
						isOwner = rs.getInt(3);
					}
				}
			}
			else{
				throw new Exception("Cannot establish connection with the database");
			}
			
			if(passwordReturn != "" && emailAdd != ""){
				if((passwordReturn.equals(pwd)) && (emailAdd.equals(emailAddress))) {
					if(isOwner == 1)
						return DartConstants.OWNER;
					else
						return DartConstants.CUSTOMER;
				}
				else
				{
					return DartConstants.FALSEBOOL;
				}
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
		
	
		return "false";
		
	}
	

	@Override
	public void savePassword(String pwd,int userId) throws Exception {


		Statement cs = null;
		PreparedStatement ps = null;
        ResultSet rs = null;
		
		Connection connection = new DBConnection().getDBConnection();
		
		try {
		
			if (connection != null) {
				
				connection.setAutoCommit(false);
				
				cs = connection.createStatement();
				
				ps = connection.prepareStatement("UPDATE USER SET password = ? WHERE userId = ?");
				ps.setString(1, pwd);
			    ps.setInt(2, userId);
				ps.executeUpdate();
				
				connection.commit();
				
			}
			else{
				throw new Exception("Cannot establish connection with the database");
			}
			

		}
		catch(SQLException e){
			throw new Exception("Cannot establish connection with the database" + e.getMessage());
		}
		finally{
			try {
				if(ps != null)
					ps.close();
				if(rs != null)
					rs.close();
				if(cs != null)
					cs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}
		}
		
		
	}

	@Override
	public Integer getEmailAddress(String emailAddress) throws Exception {
		
		
		Integer userId = null;
		Statement cs = null;
        ResultSet rs = null;
		
		Connection connection = new DBConnection().getDBConnection();
		
		try {
			cs = connection.createStatement();

			if (connection != null) {
				
	            rs = cs.executeQuery("SELECT USERID FROM USER Where emailAddress = '" + emailAddress + "'");
				if(rs != null){
					while(rs.next()){
						userId = rs.getInt(1);
					}
				}
			}
			else{
				throw new Exception("Cannot establish connection with the database");
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

		return userId;
		
	}

	@Override
	public void saveEmailAddress(String emailAddress,int userId) throws Exception {


		Statement cs = null;
		PreparedStatement ps = null;
        ResultSet rs = null;
		
		Connection connection = new DBConnection().getDBConnection();
		
		try {
		
			if (connection != null) {
				
				connection.setAutoCommit(false);
				
				cs = connection.createStatement();
				
				ps = connection.prepareStatement("UPDATE USER SET EMAILADDRESS = ? WHERE userId = ?");
				ps.setString(1, emailAddress);
			    ps.setInt(2, userId);
				ps.executeUpdate();
				
				connection.commit();
				
			}
			else{
				throw new Exception("Cannot establish connection with the database");
			}
			

		}
		catch(SQLException e){
			throw new Exception("Cannot establish connection with the database" + e.getMessage());
		}
		finally{
			try {
				if(ps != null)
					ps.close();
				if(rs != null)
					rs.close();
				if(cs != null)
					cs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}
		}
	}

	@Override
	public String getUserEmailAddress(int userId) throws Exception {
	
		
		String userEmail = null;
		Statement cs = null;
        ResultSet rs = null;
		
		Connection connection = new DBConnection().getDBConnection();
		
		try {
			cs = connection.createStatement();

			if (connection != null) {
				
	            rs = cs.executeQuery("SELECT EmailAddress FROM USER Where USERID = '" + userId + "'");
				if(rs != null){
					while(rs.next()){
						userEmail = rs.getString(1);
					}
				}
			}
			else{
				throw new Exception("Cannot establish connection with the database");
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

		return userEmail;
	}
}
