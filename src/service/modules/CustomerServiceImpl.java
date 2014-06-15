package service.modules;

import java.util.List;

import common.modules.Customer;
import common.modules.Order;
import common.modules.PaymentInfo;
import dao.modules.CustomerDao;
import dao.modules.OrderDao;

public class CustomerServiceImpl implements CustomerService{
	
	private CustomerDao customerDao;
	private OrderDao orderDao;
	
	public CustomerServiceImpl(CustomerDao customerDao, OrderDao orderDao) {
		this.customerDao = customerDao;
		this.orderDao = orderDao;
	}
	
	public CustomerServiceImpl(){
		
	}

	@Override
	public Customer getCustomer(String emailAddress) throws Exception {
		return(customerDao.getCustomer(emailAddress));
	}
	
	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	@Override
	public List<Order> getAllOrders(int customerId) throws Exception {
		return orderDao.getAllOrders(customerId);
	}

	@Override
	public List<PaymentInfo> getAllPaymentInfo(int customerId) throws Exception {
		return customerDao.getAllPaymentInfo(customerId);
	}

	@Override
	public Customer getCustomerInfo(int customerId) throws Exception {
		return customerDao.getCustomerInfo(customerId);
	}

	@Override
	public void updateCustomerInfo(Customer customer, int customerId)
			throws Exception {
		customerDao.updateCustomerInfo(customer, customerId);		
	}

	@Override
	public List<Customer> getAllCustomers() throws Exception {
		return customerDao.getAllCustomers();
	}

	
}
