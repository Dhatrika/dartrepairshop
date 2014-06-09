package common.modules;

import java.util.List;

public class Order {
	
	private Integer orderId;
	private Integer customerId;
	private String itemName;
	private String orderDate;
	private String statusName;
	private String ratingName;
	private String comments;
	private String priority;
	private Double actualCost; 
	private List<Part> orderParts;
	private Integer itemId;
	private Integer statusId;
	private Boolean isPrior;
	private Integer priorInt;
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}	
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getRatingName() {
		return ratingName;
	}
	public void setRatingName(String ratingName) {
		this.ratingName = ratingName;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public Double getActualCost() {
		return actualCost;
	}
	public void setActualCost(Double actualCost) {
		this.actualCost = actualCost;
	}
	public List<Part> getOrderParts() {
		return orderParts;
	}
	public void setOrderParts(List<Part> orderParts) {
		this.orderParts = orderParts;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public Boolean getIsPrior() {
		return isPrior;
	}
	public void setIsPrior(Boolean isPrior) {
		this.isPrior = isPrior;
	}
	public Integer getPriorInt() {
		return priorInt;
	}
	public void setPriorInt(Integer priorInt) {
		this.priorInt = priorInt;
	}
	public Order(){
		
	}

}
