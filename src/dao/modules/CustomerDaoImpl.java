package dao.modules;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.modules.Customer;

public class CustomerDaoImpl implements CustomerDao{
	
	@Override
	public Customer getCustomer(String emailAddress) throws Exception{
		
		Customer cust = null;
		Statement cs = null;
        ResultSet rs = null;
		
		Connection connection = new DBConnection().getDBConnection();
		
		try {
			cs = connection.createStatement();

			if (connection != null) {
				
	            rs = cs.executeQuery("SELECT customerId,emailAddress,phoneNumber,customerName FROM AllCustomers WHERE emailAddress = '" + emailAddress + "'");
				if(rs != null){
					while(rs.next()){
						cust = new Customer();
						cust.setCustomerId(rs.getInt(1));
						cust.setEmailAddress(rs.getString(2));
						cust.setPhoneNumber(rs.getString(3));
						cust.setCustomerName(rs.getString(4));
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
