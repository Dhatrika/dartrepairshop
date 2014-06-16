package service.modules;

import java.util.List;

import common.modules.Order;

public interface OrderService {
	public int saveOrder(boolean isNew,Order ord, int partId) throws Exception;
	public Order getCompleteOrder(int orderId) throws Exception;
	public int updateOrderStatus(int orderId,int statusId, String comments) throws Exception;
	public int updateOrderPaymentInfo(int orderId,int paymentId,int statusId) throws Exception;
	public List<Order> searchOrders(String orderDate, String status, String customerName) throws Exception;
}
