package dao.modules;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.modules.Owner;

public class OwnerDaoImpl implements OwnerDao{

	@Override
	public Owner getOwner(String emailAddress) throws Exception {
		
		Owner cust = null;
		Statement cs = null;
        ResultSet rs = null;
		
		Connection connection = new DBConnection().getDBConnection();
		
		try {
			cs = connection.createStatement();

			if (connection != null) {
				
	            rs = cs.executeQuery("SELECT ownerId,ownerName,emailAddress FROM AllOwners WHERE emailAddress = '" + emailAddress + "'");
				if(rs != null){
					while(rs.next()){
						cust = new Owner();
						cust.setOwnerId(rs.getInt(1));
						cust.setOwnerName(rs.getString(2));
						cust.setEmailAddress(rs.getString(3));
					}
				}
			}
			else{
				throw new Exception("Cannot establish connection with the database");
			}
			
			return cust;

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
		
	}
	
	

}
