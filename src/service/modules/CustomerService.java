package service.modules;

import java.util.List;

import common.modules.Customer;
import common.modules.Order;

public interface CustomerService {
	public Customer getCustomer(String emailAddress) throws Exception;
	public List<Order> getAllOrders(int customerId) throws Exception;
}
