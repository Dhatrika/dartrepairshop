package dao.modules;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.modules.Customer;
import common.modules.PaymentInfo;

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
	
	@Override
	public List<PaymentInfo> getAllPaymentInfo(int customerId) throws Exception {

		List<PaymentInfo> allPayments = new ArrayList<PaymentInfo>();
		Statement cs = null;
        ResultSet partsrs = null;
		
		Connection connection = new DBConnection().getDBConnection();
		
		try {
			cs = connection.createStatement();

			if (connection != null) {
				
				
				partsrs = cs.executeQuery("SELECT customerPaymentInfoId, customerId, bankName, accountNumber, cardNumber, (bankName || ' ' || accountNumber || ' ' || cardNumber) totalInfo FROM CustomerPaymentInfo Where customerId = " + customerId);
				if(partsrs != null){
					while(partsrs.next()){
						PaymentInfo pt = new PaymentInfo();
						pt.setPaymentInfoId(partsrs.getInt(1));
						pt.setCustomerId(partsrs.getInt(2));
						pt.setBankName(partsrs.getString(3));
						pt.setAccountNumber(partsrs.getString(4));
						pt.setCardNumber(partsrs.getString(5));
						pt.setTotalInfo(partsrs.getString(6));
						allPayments.add(pt);
						}
				}
				
			}
			else{
				throw new Exception("Cannot establish connection with the database");
			}
			
			return allPayments;

		}
		catch(SQLException e){
			throw new Exception("Cannot establish connection with the database" + e.getMessage());
		}
		finally{
			try {
				if(partsrs != null)
					partsrs.close();
				if(cs != null)
					cs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}
		}
	}

}
