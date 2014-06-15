package web.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import service.modules.CustomerService;
import service.modules.LoginValidate;
import service.modules.OwnerService;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import common.modules.Customer;
import common.modules.Owner;
import common.modules.User;
import common.util.DartConstants;


@SuppressWarnings("serial")
public class Login extends ActionSupport implements ServletRequestAware, ServletResponseAware{
	
	   private HttpServletRequest request;
	   private HttpServletResponse response;
	   private User loginUser;
	   private LoginValidate loginValidate;
	   private CustomerService customerService;
	   private OwnerService ownerService;
	   private Customer customer;
	   private Owner owner;
	   
	   public Login(){
		   
	   }
	
	public Login(LoginValidate loginValidate, CustomerService customerService, OwnerService ownerService) {
		this.loginValidate = loginValidate;
		this.customerService = customerService;
		this.ownerService = ownerService;
	}

	public LoginValidate getLoginValidate() {
		return loginValidate;
	}

	public void setLoginValidate(LoginValidate loginValidate) {
		this.loginValidate = loginValidate;
	}

	public String execute() throws Exception{
		
		response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        
        String checkValidation = loginValidate.passwordValidate(loginUser.getPassword(),loginUser.getEmailAddress());
        if(checkValidation.equals(DartConstants.FALSEBOOL)){
        	return Action.ERROR;
        }
        else{
        	if(checkValidation.equals(DartConstants.CUSTOMER)){
        		customer = customerService.getCustomer(loginUser.getEmailAddress());
        	}
        	else{
        		owner = ownerService.getOwner(loginUser.getEmailAddress()); 
        	}
        	return checkValidation;
        	
        }
		
	}
	
	public String internalLoginRedirect(){
		return Action.SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
		
	}

	public User getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(User loginUser) {
		this.loginUser = loginUser;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public OwnerService getOwnerService() {
		return ownerService;
	}

	public void setOwnerService(OwnerService ownerService) {
		this.ownerService = ownerService;
	}

}
