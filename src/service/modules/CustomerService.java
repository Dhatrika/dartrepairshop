package service.modules;

import java.util.List;

import common.modules.Customer;
import common.modules.Order;
import common.modules.PaymentInfo;

public interface CustomerService {
	public Customer getCustomer(String emailAddress) throws Exception;
	public List<Order> getAllOrders(int customerId) throws Exception;
	public List<PaymentInfo> getAllPaymentInfo(int customerId) throws Exception;
}
