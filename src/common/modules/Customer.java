package common.modules;

public class Customer extends User{

	private Integer customerId;
	private String customerName;
	private String phoneNumber;
	private Address addr;
	private PaymentInfo payInfo;
	private String title;
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public Address getAddr() {
		return addr;
	}
	public void setAddr(Address addr) {
		this.addr = addr;
	}
	public PaymentInfo getPayInfo() {
		return payInfo;
	}
	public void setPayInfo(PaymentInfo payInfo) {
		this.payInfo = payInfo;
	}
	public Customer(){
		this.addr = new Address();
		this.payInfo = new PaymentInfo();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
