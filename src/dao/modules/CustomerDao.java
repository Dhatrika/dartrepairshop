package dao.modules;

import common.modules.Customer;

public interface CustomerDao {
	public Customer getCustomer(String emailAddress) throws Exception;
}
