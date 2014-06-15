package dao.modules;

import java.util.List;

import common.modules.Customer;
import common.modules.PaymentInfo;

public interface CustomerDao {
	public Customer getCustomer(String emailAddress) throws Exception;
	public List<PaymentInfo> getAllPaymentInfo(int customerId) throws Exception;
	public Customer getCustomerInfo(int customerId) throws Exception;
	public void updateCustomerInfo(Customer customer, int customerId) throws Exception;
	public List<Customer> getAllCustomers() throws Exception;
}
