package web.action;


import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import service.modules.CustomerService;
import service.modules.LoginValidate;
import service.modules.OwnerService;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import common.modules.Customer;
import common.modules.Owner;
import common.modules.User;
import common.util.DartConstants;
import common.util.MailWrap;


@SuppressWarnings("serial")
public class Login extends ActionSupport implements ServletRequestAware, ServletResponseAware, SessionAware{
	
	   private HttpServletRequest request;
	   private HttpServletResponse response;
	   private User loginUser;
	   private LoginValidate loginValidate;
	   private CustomerService customerService;
	   private OwnerService ownerService;
	   private Customer customer;
	   private Owner owner;
	   private Map<String, Object> sessionMap;
	   private Integer userId;
	   private String internalPwd;
	   
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

	public String home(){
		response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        
		if (sessionMap.containsKey("emailAddress")){
			return Action.LOGIN;
		}
		return Action.SUCCESS;
	}
	
	public String execute() throws Exception{
		
		response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        
        if (sessionMap.containsKey("emailAddress")){
        	customer = (Customer) sessionMap.get("customer");
        	owner = (Owner) sessionMap.get("owner");
        	String checkValidation = (String) sessionMap.get("checkValidation");
        	return checkValidation;
        }
        
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
        	sessionMap.put("customer", customer);
        	sessionMap.put("owner", owner);
        	sessionMap.put("checkValidation", checkValidation);
        	sessionMap.put("emailAddress", loginUser.getEmailAddress());
        	return checkValidation;
        	
        }
		
	}
	
	public String logOut(){
		if (sessionMap.containsKey("customer")) {
            sessionMap.remove("customer");
        }
		if (sessionMap.containsKey("owner")) {
            sessionMap.remove("owner");
        }
		if (sessionMap.containsKey("checkValidation")) {
            sessionMap.remove("checkValidation");
        }
		if (sessionMap.containsKey("emailAddress")) {
            sessionMap.remove("emailAddress");
        }
		return Action.SUCCESS;
	}
	
	public String resetPassword() throws Exception{
		
		response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        
        String strCheckEmail = request.getParameter("checkEmail");
        if(strCheckEmail != null){
        	Integer usrId = loginValidate.getEmailAddress(strCheckEmail);
        	if(usrId != null){
        			//generate the password 
        			String newPwd = generateRandomPassword();
        			
        			//save the password
        			loginValidate.savePassword(newPwd, usrId);
        			
        			//email the password
        			sendEmail(DartConstants.FROMEMAILID, DartConstants.FROMEMAILPWD, strCheckEmail, DartConstants.SUBJECT, newPwd);
        			return Action.SUCCESS;        		
        	}
        	else{
        		return "invalid";
        	}
        }
        String strUserId = request.getParameter("userId");
        if(strUserId != null){
        	Integer usrId = Integer.valueOf(strUserId);
        	userId = usrId;
        	internalPwd = "";
        	return "internal";
        }
        
        return "invalid";
        
	}
	
	public String internalResetPassword() throws Exception{
		if(userId != null && internalPwd != null){
			loginValidate.savePassword(internalPwd, userId);
			return Action.SUCCESS;
		}
		return "error";
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
		
	}
	
    @Override
    public void setSession(Map<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
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

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getInternalPwd() {
		return internalPwd;
	}

	public void setInternalPwd(String internalPwd) {
		this.internalPwd = internalPwd;
	}

	public String generateRandomPassword(){
		int length = 6;		
	       final String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJLMNOPQRSTUVWXYZ1234567890";
	       StringBuilder result = new StringBuilder();
	       while(length > 0) {
	           Random rand = new Random();
	           result.append(characters.charAt(rand.nextInt(characters.length())));
	           length--;
	       }
	       return result.toString();
	}
	
	public void sendEmail(String from, String pass, String to, String subject, String body){
		String[] toAddr = { to };
		if(to != null && to != ""){
        	MailWrap.sendFromGMail(from, pass, toAddr, subject, body); //send the email
        }
		
	}

}
