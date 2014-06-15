package dao.modules;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.modules.Item;
import common.modules.Order;
import common.modules.Part;
import common.modules.PaymentInfo;
import common.modules.Status;

public class OrderDaoImpl implements OrderDao{

	@Override
	public List<Order> getAllOrders(int customerId) throws Exception {
		List<Order> allOrders = new ArrayList<Order>();
		Statement cs = null;
        ResultSet rs = null;
		
		Connection connection = new DBConnection().getDBConnection();
		
		try {
			cs = connection.createStatement();

			if (connection != null) {
				
	            rs = cs.executeQuery("SELECT customerId,customerOrderId,itemName,OrderDate,statusName,ratingName,actualCost,Comments,priority,statusId,isPaid,totalInfo FROM AllOrders WHERE customerId = '" + customerId + "'");
				if(rs != null){
					while(rs.next()){
						Order ord = new Order();
						ord.setCustomerId(rs.getInt(1));
						ord.setOrderId(rs.getInt(2));
						ord.setItemName(rs.getString(3));
						ord.setOrderDate(rs.getString(4));
						ord.setStatusName(rs.getString(5));
						ord.setRatingName(rs.getString(6));
						ord.setActualCost(rs.getDouble(7));
						ord.setComments(rs.getString(8));
						int priority = rs.getInt(9);
						if(priority == 0)
						{
							ord.setPriority("No");
						}
						else{
							ord.setPriority("Yes");
						}
						ord.setStatusId(rs.getInt(10));
						int paidInfo = rs.getInt(11);
						if(paidInfo == 0)
						{
							ord.setPaidInfo("No");
						}
						else{
							ord.setPaidInfo("Yes");
						}
						ord.setPaymentInfo(rs.getString(12));
						allOrders.add(ord);
					}
				}
			}
			else{
				throw new Exception("Cannot establish connection with the database");
			}
			
			return allOrders;

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
	public Order getCompleteOrder(int orderId) throws Exception {

		Order ord = new Order();
		Statement cs = null;
        ResultSet rs = null;
        ResultSet partsrs = null;
		
		Connection connection = new DBConnection().getDBConnection();
		
		try {
			cs = connection.createStatement();

			if (connection != null) {
				
	            rs = cs.executeQuery("SELECT customerId,customerOrderId,itemName,OrderDate,statusName,ratingName,actualCost,Comments,priority,statusId,isPaid,totalInfo FROM AllOrders WHERE customerOrderId = '" + orderId + "'");
				if(rs != null){
					while(rs.next()){
						ord.setCustomerId(rs.getInt(1));
						ord.setOrderId(rs.getInt(2));
						ord.setItemName(rs.getString(3));
						ord.setOrderDate(rs.getString(4));
						ord.setStatusName(rs.getString(5));
						ord.setRatingName(rs.getString(6));
						ord.setActualCost(rs.getDouble(7));
						ord.setComments(rs.getString(8));
						int priority = rs.getInt(9);
						if(priority == 0)
						{
							ord.setPriority("No");
						}
						else{
							ord.setPriority("Yes");
						}
						ord.setStatusId(rs.getInt(10));
						int paidInfo = rs.getInt(11);
						if(paidInfo == 0)
						{
							ord.setPaidInfo("No");
						}
						else{
							ord.setPaidInfo("Yes");
						}
						ord.setPaymentInfo(rs.getString(12));
					}
				}
				
				
				partsrs = cs.executeQuery("SELECT Partid, PartName, brand, actualPrice FROM OrderParts WHERE customerOrderId = '" + orderId + "'");
				List<Part> allParts = new ArrayList<Part>();
				if(partsrs != null){
					while(partsrs.next()){
						Part pt = new Part();
						pt.setPartId(partsrs.getInt(1));
						pt.setPartName(partsrs.getString(2));
						pt.setBrand(partsrs.getString(3));
						pt.setPrice(partsrs.getDouble(4));
						allParts.add(pt);
						}
				}
				
				ord.setOrderParts(allParts);
				
			}
			else{
				throw new Exception("Cannot establish connection with the database");
			}
			
			return ord;

		}
		catch(SQLException e){
			throw new Exception("Cannot establish connection with the database" + e.getMessage());
		}
		finally{
			try {
				if(rs != null)
					rs.close();
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
	public List<Item> getAllItems() throws Exception {
		List<Item> allItems = new ArrayList<Item>();
		Statement cs = null;
        ResultSet rs = null;
		
		Connection connection = new DBConnection().getDBConnection();
		
		try {
			cs = connection.createStatement();

			if (connection != null) {
				
	            rs = cs.executeQuery("SELECT itemId,itemName from Item");
				if(rs != null){
					while(rs.next()){
						Item it = new Item();
						it.setItemId(rs.getInt(1));
						it.setItemName(rs.getString(2));
						allItems.add(it);
					}
				}
				
			}
			else{
				throw new Exception("Cannot establish connection with the database");
			}
			
			return allItems;

		}
		catch(SQLException e){
			throw new Exception("Cannot establish connection with the database" + e.getMessage());
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
	public List<Status> getAllOrderStates() throws Exception {
		List<Status> allStatus = new ArrayList<Status>();
		Statement cs = null;
        ResultSet rs = null;
		
		Connection connection = new DBConnection().getDBConnection();
		
		try {
			cs = connection.createStatement();

			if (connection != null) {
				
	            rs = cs.executeQuery("SELECT statusId,statusName from Status");
				if(rs != null){
					while(rs.next()){
						Status st = new Status();
						st.setStatusId(rs.getInt(1));
						st.setStatusName(rs.getString(2));
						allStatus.add(st);
					}
				}
				
			}
			else{
				throw new Exception("Cannot establish connection with the database");
			}
			
			return allStatus;

		}
		catch(SQLException e){
			throw new Exception("Cannot establish connection with the database" + e.getMessage());
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
	public List<String> getAllRatings() throws Exception {
		List<String> allRatings = new ArrayList<String>();
		Statement cs = null;
        ResultSet rs = null;
		
		Connection connection = new DBConnection().getDBConnection();
		
		try {
			cs = connection.createStatement();

			if (connection != null) {
				
	            rs = cs.executeQuery("SELECT ratingName from Rating");
				if(rs != null){
					while(rs.next()){
						allRatings.add(rs.getString(1));
					}
				}
				
			}
			else{
				throw new Exception("Cannot establish connection with the database");
			}
			
			return allRatings;

		}
		catch(SQLException e){
			throw new Exception("Cannot establish connection with the database" + e.getMessage());
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
	public List<Part> getAllRepairParts() throws Exception {
		List<Part> allParts = new ArrayList<Part>();
		Statement cs = null;
        ResultSet partsrs = null;
		
		Connection connection = new DBConnection().getDBConnection();
		
		try {
			cs = connection.createStatement();

			if (connection != null) {
				
				
				partsrs = cs.executeQuery("SELECT PartId, PartName FROM Part");
				if(partsrs != null){
					while(partsrs.next()){
						Part pt = new Part();
						pt.setPartId(partsrs.getInt(1));
						pt.setPartName(partsrs.getString(2));
						allParts.add(pt);
						}
				}
				
			}
			else{
				throw new Exception("Cannot establish connection with the database");
			}
			
			return allParts;

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
	public int saveOrder(boolean isNew, Order ord, int partId) throws Exception {
		
		Statement cs = null;
		PreparedStatement ps = null;
        ResultSet rs = null;
        int tobeReturned = 0;
        double price = 0;
		
		Connection connection = new DBConnection().getDBConnection();
		
		try {
		
			if (connection != null) {
				
				cs = connection.createStatement();
				rs = cs.executeQuery("SELECT actualPrice FROM PART where partId = " + partId);
				price = rs.getDouble(1);
				
				if(ord.getPriorInt() == 1){
					price = price + 5; //extra 5 for priority service
				}
				
			    connection.setAutoCommit(false);
			    
			    if(isNew){
			    	ps = connection.prepareStatement("INSERT INTO CustomerOrder(customerId,orderDate,itemId,statusId,actualCost,comments,priority) VALUES(?,?,?,?,?,?,?)");
					ps.setInt(1, ord.getCustomerId());
					ps.setString(2, ord.getOrderDate());
					ps.setInt(3, ord.getItemId());
					ps.setInt(4, ord.getStatusId());
					ps.setDouble(5, price);
					ps.setString(6, ord.getComments());
					ps.setInt(7, ord.getPriorInt());
					ps.executeUpdate();
			    }
				
				
				rs = cs.executeQuery("SELECT last_insert_rowid()");
				tobeReturned = rs.getInt(1);
				
				if(partId != 0){
					ps = connection.prepareStatement("INSERT INTO OrderPartInfo(customerOrderId,partId) VALUES(?,?)");
					ps.setInt(1, tobeReturned);
					ps.setInt(2, partId);
					ps.executeUpdate();
				}
				
				connection.commit();
				
			}
			else{
				throw new Exception("Cannot establish connection with the database");
			}
			
			return tobeReturned;

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
	public int updateOrderStatus(int orderId,int statusId,String comments) throws Exception {
		
		Statement cs = null;
		PreparedStatement ps = null;
        ResultSet rs = null;
        int tobeReturned = 0;
		
		Connection connection = new DBConnection().getDBConnection();
		
		try {
		
			if (connection != null) {
				
				cs = connection.createStatement();
				
				
			    connection.setAutoCommit(false);
			    
			    ps = connection.prepareStatement("UPDATE CustomerOrder SET statusId = ?,comments = ? WHERE customerOrderId = ?");
			    ps.setInt(1, statusId);
			    ps.setString(2, comments);
			    ps.setInt(3, orderId);
			    ps.executeUpdate();
				
				tobeReturned = orderId;
				
				connection.commit();
				
			}
			else{
				throw new Exception("Cannot establish connection with the database");
			}
			
			return tobeReturned;

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
	public int updateOrderPaymentInfo(int orderId, int paymentId,int statusId)
			throws Exception {
		
		Statement cs = null;
		PreparedStatement ps = null;
        ResultSet rs = null;
        int tobeReturned = 0;
		
		Connection connection = new DBConnection().getDBConnection();
		
		try {
		
			if (connection != null) {
				
				cs = connection.createStatement();
				
				
			    connection.setAutoCommit(false);
			    
			    ps = connection.prepareStatement("UPDATE CustomerOrder SET isPaid = 1, paymentInfoId = ?, statusId= ? WHERE customerOrderId = ?");
			    ps.setInt(1, paymentId);
			    ps.setInt(2, statusId);
			    ps.setInt(3, orderId);
			    ps.executeUpdate();
				
				tobeReturned = orderId;
				
				connection.commit();
				
			}
			else{
				throw new Exception("Cannot establish connection with the database");
			}
			
			return tobeReturned;

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



}
