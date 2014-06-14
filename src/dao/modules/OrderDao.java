package dao.modules;

import java.util.List;

import common.modules.Item;
import common.modules.Order;
import common.modules.Part;
import common.modules.PaymentInfo;
import common.modules.Status;

public interface OrderDao {
		public List<Order> getAllOrders(int customerId) throws Exception;
		public Order getCompleteOrder(int orderId) throws Exception;
		public List<Status> getAllOrderStates() throws Exception;
		public List<Item> getAllItems() throws Exception;
		public List<String> getAllRatings() throws Exception;
		public List<Part> getAllRepairParts() throws Exception;
		public int saveOrder(boolean isNew, Order ord, int partId) throws Exception;
		public int updateOrderStatus(int orderId,int statusId) throws Exception;
		public int updateOrderPaymentInfo(int orderId,int paymentId,int statusId) throws Exception;
		
}
