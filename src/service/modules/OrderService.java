package service.modules;

import common.modules.Order;

public interface OrderService {
	public int saveOrder(boolean isNew,Order ord, int partId) throws Exception;
	public Order getCompleteOrder(int orderId) throws Exception;
}
