package dao.modules;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

	@Override
	public Customer getCustomerInfo(int customerId) throws Exception {
		
		Customer cust = null;
		Statement cs = null;
        ResultSet rs = null;
		
		Connection connection = new DBConnection().getDBConnection();
		
		try {
			cs = connection.createStatement();

			if (connection != null) {
				
	            rs = cs.executeQuery("SELECT customerId,emailAddress,phoneNumber,fullName,title FROM AllCustomers WHERE customerId = " + customerId);
				if(rs != null){
					while(rs.next()){
						cust = new Customer();
						cust.setCustomerId(rs.getInt(1));
						cust.setEmailAddress(rs.getString(2));
						cust.setPhoneNumber(rs.getString(3));
						cust.setCustomerName(rs.getString(4));
						cust.setTitle(rs.getString(5));					
				}
				
				rs = cs.executeQuery("SELECT addressId, street, state, city, postalcode FROM AllCustomerAddress WHERE customerId = " + customerId + " AND isprimary = 1");
				if(rs != null){
					while(rs.next()){
						cust.getAddr().setAddressId(rs.getInt(1));
						cust.getAddr().setStreet(rs.getString(2));
						cust.getAddr().setState(rs.getString(3));
						cust.getAddr().setCity(rs.getString(4));
						cust.getAddr().setPostalCode(rs.getString(5));
					}
				}
				
				rs = cs.executeQuery("SELECT customerPaymentInfoId, customerId, bankName, accountNumber, cardNumber, (bankName || ' ' || accountNumber || ' ' || cardNumber) totalInfo FROM CustomerPaymentInfo Where customerId = " + customerId + " AND isprimary = 1");
				if(rs != null){
					while(rs.next()){
						cust.getPayInfo().setPaymentInfoId(rs.getInt(1));
						cust.getPayInfo().setCustomerId(rs.getInt(2));
						cust.getPayInfo().setBankName(rs.getString(3));
						cust.getPayInfo().setAccountNumber(rs.getString(4));
						cust.getPayInfo().setCardNumber(rs.getString(5));
						cust.getPayInfo().setTotalInfo(rs.getString(6));
					}
				}
			}
			else{
				throw new Exception("Cannot establish connection with the database");
			}

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
	public void updateCustomerInfo(Customer customer, int customerId) throws Exception {
		
		Statement cs = null;
		PreparedStatement ps = null;
        ResultSet rs = null;
        Integer addressId = null;
        Integer primaryPaymentInfoId = null;
		
		Connection connection = new DBConnection().getDBConnection();
		
		try {
		
			if (connection != null) {
				
				connection.setAutoCommit(false);
				
				cs = connection.createStatement();
				rs = cs.executeQuery("SELECT addressId FROM Address where street = '" + customer.getAddr().getStreet() + "' AND " +
						" city = '" + customer.getAddr().getCity() + "' AND" +
						" state = '" + customer.getAddr().getState() + "' AND" +
						" postalcode = '" + customer.getAddr().getPostalCode() + "'");
				if(rs != null){
					while(rs.next())
					addressId = rs.getInt(1);
				}
				
				if(addressId != null){
					if(addressId != 0){
						ps = connection.prepareStatement("UPDATE CUSTOMERADDRESSMAPPING SET ISPRIMARY = 0 WHERE CustomerId = " + customerId);
						ps.executeUpdate();
						ps = connection.prepareStatement("INSERT INTO CUSTOMERADDRESSMAPPING(customerId,addressId,isprimary) VALUES (?,?,?)");
						ps.setInt(1, customerId);
					    ps.setInt(2, addressId);
					    ps.setInt(3, 1);
						ps.executeUpdate();
						
					}
				}
				else{
					 ps = connection.prepareStatement("INSERT INTO ADDRESS(STREET,CITY,STATE,POSTALCODE) VALUES(?,?,?,?)");
					 ps.setString(1, customer.getAddr().getStreet());
					 ps.setString(2, customer.getAddr().getCity());
					 ps.setString(3, customer.getAddr().getState());
					 ps.setString(4, customer.getAddr().getPostalCode());
					 ps.executeUpdate();
					 
					 rs = cs.executeQuery("SELECT last_insert_rowid()");
					 int addressIdTobeInserted = rs.getInt(1);
					 
					 	ps = connection.prepareStatement("INSERT INTO CUSTOMERADDRESSMAPPING(customerId,addressId,isprimary) VALUES (?,?,?)");
						ps.setInt(1, customerId);
					    ps.setInt(2, addressIdTobeInserted);
					    ps.setInt(3, 1);
						ps.executeUpdate();
				}
				
			  

			    ps = connection.prepareStatement("UPDATE USER SET EMAILADDRESS = '" + customer.getEmailAddress() + "' Where UserId = " + customerId);
			    ps.executeUpdate();
			    
			    ps = connection.prepareStatement("UPDATE CUSTOMER SET title =?, customerName =?, phoneNumber=? Where customerId = ?");
			    ps.setString(1, customer.getTitle());
			    ps.setString(2, customer.getCustomerName());
			    ps.setString(3, customer.getPhoneNumber());
			    ps.setInt(4, customerId);
			    ps.executeUpdate();
			    
			    //paymentInfo update
				rs = cs.executeQuery("SELECT customerPaymentInfoId FROM CustomerPaymentInfo where bankName = '" + customer.getPayInfo().getBankName() + "' AND " +
						" accountNumber = '" + customer.getPayInfo().getAccountNumber() + "' AND" +
						" cardNumber = '" + customer.getPayInfo().getCardNumber() + "' AND" +
						" customerId = " + customerId);
				if(rs != null){
					while(rs.next())
					primaryPaymentInfoId = rs.getInt(1);
				}
				if(primaryPaymentInfoId != null){
					if(primaryPaymentInfoId != 0){

						ps = connection.prepareStatement("UPDATE CustomerPaymentInfo SET ISPRIMARY = 0 WHERE CustomerId = " + customerId);
						ps.executeUpdate();
						ps = connection.prepareStatement("UPDATE CustomerPaymentInfo SET ISPRIMARY = 1 WHERE CustomerId = " + customerId + " AND customerPaymentInfoId  = " + primaryPaymentInfoId);
						ps.executeUpdate();						
					}
				}
				else{

					ps = connection.prepareStatement("UPDATE CustomerPaymentInfo SET ISPRIMARY = 0 WHERE CustomerId = " + customerId);
					ps.executeUpdate();
					ps = connection.prepareStatement("INSERT INTO CustomerPaymentInfo(customerId,bankName,accountNumber,cardNumber,isprimary) VALUES (?,?,?,?,?)");
					ps.setInt(1, customerId);
				    ps.setString(2, customer.getPayInfo().getBankName());
				    ps.setString(3, customer.getPayInfo().getAccountNumber());
				    ps.setString(4, customer.getPayInfo().getCardNumber());
				    ps.setInt(5, 1);
					ps.executeUpdate();
					
				}
				
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
	public List<Customer> getAllCustomers() throws Exception {
		
		List<Customer> allCustomers = new ArrayList<Customer>();
		Statement cs = null;
        ResultSet rs = null;
		
		Connection connection = new DBConnection().getDBConnection();
		
		try {
			cs = connection.createStatement();

			if (connection != null) {
				
	            rs = cs.executeQuery("SELECT customerId,emailAddress,phoneNumber,customerName FROM AllCustomers ");
				if(rs != null){
					while(rs.next()){
						Customer cust = new Customer();
						cust.setCustomerId(rs.getInt(1));
						cust.setEmailAddress(rs.getString(2));
						cust.setPhoneNumber(rs.getString(3));
						cust.setCustomerName(rs.getString(4));
						allCustomers.add(cust);
					}
				}
			}
			else{
				throw new Exception("Cannot establish connection with the database");
			}
			
			return allCustomers;

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
