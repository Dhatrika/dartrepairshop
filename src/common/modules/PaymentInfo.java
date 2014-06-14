package common.modules;

public class PaymentInfo {
	
	private int paymentInfoId;
	private int customerId;
	private String bankName;
	private String accountNumber;
	private String cardNumber;
	private String totalInfo;
	
	public int getPaymentInfoId() {
		return paymentInfoId;
	}
	public void setPaymentInfoId(int paymentInfoId) {
		this.paymentInfoId = paymentInfoId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}	
	public String getTotalInfo() {
		return totalInfo;
	}
	public void setTotalInfo(String totalInfo) {
		this.totalInfo = totalInfo;
	}
	public PaymentInfo(){
		
	}

}
