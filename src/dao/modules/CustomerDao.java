package dao.modules;

import java.util.List;

import common.modules.Customer;
import common.modules.PaymentInfo;

public interface CustomerDao {
	public Customer getCustomer(String emailAddress) throws Exception;
	public List<PaymentInfo> getAllPaymentInfo(int customerId) throws Exception;
}
