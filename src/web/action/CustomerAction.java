package web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import service.modules.CustomerService;
import service.modules.ReferenceData;

import com.opensymphony.xwork2.ActionSupport;

import common.modules.Customer;
import common.modules.Order;

@SuppressWarnings("serial")
public class CustomerAction extends ActionSupport implements ServletRequestAware, ServletResponseAware{
	
	   private HttpServletRequest request;
	   private HttpServletResponse response;
	   private CustomerService customerService;
	   private ReferenceData referenceData;
	   private List<Order> allOrders;
	   private int customerId;
	   private Order completeOrder;
	   private Customer customer;
	   List<Customer> allCustomers;
	   private Boolean ownerRelated;
	   
	   @Override
		public void setServletRequest(HttpServletRequest arg0) {
			this.request = arg0;
			
		}

		@Override
		public void setServletResponse(HttpServletResponse arg0) {
			this.response = arg0;
			
		}
		
		public CustomerAction(){
			
		}

		public CustomerService getCustomerService() {
			return customerService;
		}

		public void setCustomerService(CustomerService customerService) {
			this.customerService = customerService;
		}

		public CustomerAction(CustomerService customerService, ReferenceData referenceData) {
			this.customerService = customerService;
			this.referenceData = referenceData;
		}
		
		public String showAllOrders() throws Exception{
			
			response.setHeader("Cache-Control", "no-store");
	        response.setHeader("Pragma", "no-cache");
	        response.setDateHeader("Expires", 0);
	        String customerStringId = request.getParameter("customerId");
	        int custId = Integer.valueOf(customerStringId);
	        String ownerStringRelated = request.getParameter("ownerRelated");
	        ownerRelated = Boolean.valueOf(ownerStringRelated);
	        customerId = custId;
	        allOrders = customerService.getAllOrders(custId);
	        return SUCCESS;
			
		}
		
		public String loadProfile() throws Exception{
			response.setHeader("Cache-Control", "no-store");
	        response.setHeader("Pragma", "no-cache");
	        response.setDateHeader("Expires", 0);
	        
	        String customerStringId = request.getParameter("customerId");
	        int custId = Integer.valueOf(customerStringId);
	        String ownerStringRelated = request.getParameter("ownerRelated");
	        ownerRelated = Boolean.valueOf(ownerStringRelated);
	        
	        customerId = custId;
	        customer = customerService.getCustomerInfo(customerId);
	        
			return SUCCESS;
		}
		
		public String saveCustomerInfo() throws Exception{
			response.setHeader("Cache-Control", "no-store");
	        response.setHeader("Pragma", "no-cache");
	        response.setDateHeader("Expires", 0);
	        String ownerStringRelated = request.getParameter("ownerRelated");
	        ownerRelated = Boolean.valueOf(ownerStringRelated);
	        customerId = customer.getCustomerId();
	        customerService.updateCustomerInfo(customer, customerId);
	        customer = customerService.getCustomerInfo(customerId);
	        
			return SUCCESS;
		}
		
		public String showAllCustomers() throws Exception{
			
			response.setHeader("Cache-Control", "no-store");
	        response.setHeader("Pragma", "no-cache");
	        response.setDateHeader("Expires", 0);
	        ownerRelated = true;
	        allCustomers = customerService.getAllCustomers();
	        return SUCCESS;
			
		}		
		
		public List<Order> getAllOrders() {
			return allOrders;
		}

		public void setAllOrders(List<Order> allOrders) {
			this.allOrders = allOrders;
		}

		public int getCustomerId() {
			return customerId;
		}

		public void setCustomerId(int customerId) {
			this.customerId = customerId;
		}

		public Order getCompleteOrder() {
			return completeOrder;
		}

		public void setCompleteOrder(Order completeOrder) {
			this.completeOrder = completeOrder;
		}

		public ReferenceData getReferenceData() {
			return referenceData;
		}

		public void setReferenceData(ReferenceData referenceData) {
			this.referenceData = referenceData;
		}

		public Customer getCustomer() {
			return customer;
		}

		public void setCustomer(Customer customer) {
			this.customer = customer;
		}

		public List<Customer> getAllCustomers() {
			return allCustomers;
		}

		public void setAllCustomers(List<Customer> allCustomers) {
			this.allCustomers = allCustomers;
		}

		public Boolean getOwnerRelated() {
			return ownerRelated;
		}

		public void setOwnerRelated(Boolean ownerRelated) {
			this.ownerRelated = ownerRelated;
		}
	

}
