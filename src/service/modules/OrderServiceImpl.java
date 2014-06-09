package service.modules;

import common.modules.Order;
import dao.modules.OrderDao;

public class OrderServiceImpl implements OrderService{
	
	private OrderDao orderDao;
	
	public OrderServiceImpl(){
		
	}

	public OrderServiceImpl(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	
	public OrderDao getOrderDao() {
		return orderDao;
	}


	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	@Override
	public int saveOrder(boolean isNew,Order ord, int partId) throws Exception {
		return orderDao.saveOrder(isNew, ord, partId);
	}

	@Override
	public Order getCompleteOrder(int orderId) throws Exception {
		return orderDao.getCompleteOrder(orderId);
	}
	
	
}
