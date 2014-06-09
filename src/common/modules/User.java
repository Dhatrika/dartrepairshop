package common.modules;

public class User {
	
	private Integer userId;
	private String password;
	private String emailAddress;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public User(){
		
	}
	
	public User(String emailAddress, String password, Integer userId) {
		this.emailAddress = emailAddress;
		this.password = password;
		this.userId = userId;
	}
}
