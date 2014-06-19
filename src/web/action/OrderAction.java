package web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import service.modules.CustomerService;
import service.modules.OrderService;
import service.modules.ReferenceData;

import com.opensymphony.xwork2.ActionSupport;

import common.modules.Customer;
import common.modules.Item;
import common.modules.Order;
import common.modules.Part;
import common.modules.PaymentInfo;
import common.modules.Status;
import common.util.DartConstants;

@SuppressWarnings("serial")
public class OrderAction extends ActionSupport implements ServletRequestAware, ServletResponseAware{

	   private HttpServletRequest request;
	   private HttpServletResponse response;
	   private OrderService orderService;
	   private ReferenceData referenceData;
	   private List<Part> partsList;
	   private Order completeOrder;
	   private Part orderPart;
	   private int customerId;
	   private int orderId;
	   private List<Item> itemsList;
	   private List<Status> statusList;
	   private List<Status> partialStatusList;
	   private CustomerService customerService;
	   List<PaymentInfo> paymentInfoList;
	   private Boolean ownerRelated;
	   private String orderStatus;
	   private String orderDate;
	   private List<Order> allOrders;
	   private String customersList;
	   private String searchCustomer;

	   	@Override
		public void setServletRequest(HttpServletRequest arg0) {
			this.request = arg0;
		}

		@Override
		public void setServletResponse(HttpServletResponse arg0) {
			this.response = arg0;
		}

		public OrderService getOrderService() {
			return orderService;
		}

		public void setOrderService(OrderService orderService) {
			this.orderService = orderService;
		}

		public ReferenceData getReferenceData() {
			return referenceData;
		}

		public void setReferenceData(ReferenceData referenceData) {
			this.referenceData = referenceData;
		}

		public OrderAction(OrderService orderService,
				ReferenceData referenceData,
				CustomerService customerService) {
			this.orderService = orderService;
			this.referenceData = referenceData;
			this.customerService = customerService;
		}
		
		public OrderAction(){
			
		}
		
		public String addOrder(){
			completeOrder = new Order();
			orderPart = new Part();
			String custrId = request.getParameter("customerId");
			customerId = Integer.valueOf(custrId);
			String ownerRelatedString = request.getParameter("ownerRelated");
			ownerRelated = Boolean.valueOf(ownerRelatedString);
			partsList = referenceData.getPartsList();
			itemsList = referenceData.getItems();
			statusList = referenceData.getStatuses();
			return SUCCESS;
		}
		
		public String loadOrder() throws Exception{
			response.setHeader("Cache-Control", "no-store");
	        response.setHeader("Pragma", "no-cache");
	        response.setDateHeader("Expires", 0);
	        
			String ordId = request.getParameter("orderId");
			orderId = Integer.valueOf(ordId);
			String ownerRelatedString = request.getParameter("ownerRelated");
			ownerRelated = Boolean.valueOf(ownerRelatedString);
			completeOrder = orderService.getCompleteOrder(orderId);
			statusList = referenceData.getStatuses();
			partialStatusList = new ArrayList<Status>();
			for(Status st: statusList){
				if(st.getStatusName().equals(DartConstants.ORDERPLACED) || st.getStatusName().equals(DartConstants.ORDERCANCELLED)){
					partialStatusList.add(st);
				}
			}
			return SUCCESS;
		}
		
		public String saveNewOrder() throws Exception{
			String ownerRelatedString = request.getParameter("ownerRelated");
			ownerRelated = Boolean.valueOf(ownerRelatedString);
			completeOrder.setCustomerId(customerId);
			completeOrder.setComments("Placed");
			int statusId = 0;
			for(Status st : referenceData.getStatuses()){
				if(st.getStatusName().equals(DartConstants.ORDERPLACED)){
					statusId = st.getStatusId();
				}
			}
			completeOrder.setStatusId(statusId);
			if(completeOrder.getIsPrior() != null){
				if(completeOrder.getIsPrior()){
					completeOrder.setPriorInt(1);
				}
				else{
					completeOrder.setPriorInt(0);
				}
			}
			else{
				completeOrder.setPriorInt(0);
			}
			completeOrder.setOrderDate(DartConstants.getCurrentDate());
			orderId = orderService.saveOrder(true, completeOrder, orderPart.getPartId());
			return SUCCESS;
		}
		
		
		public String saveCustomerExistingOrder() throws Exception{
			String ownerRelatedString = request.getParameter("ownerRelated");
			ownerRelated = Boolean.valueOf(ownerRelatedString);
			int ordId = completeOrder.getOrderId();
			int statusId = completeOrder.getStatusId();
			String comments = completeOrder.getComments();
			orderId = orderService.updateOrderStatus(ordId, statusId, comments);
			return SUCCESS;
		}
		
		public String loadPayment() throws Exception{
			response.setHeader("Cache-Control", "no-store");
	        response.setHeader("Pragma", "no-cache");
	        response.setDateHeader("Expires", 0);
	        
			String ownerRelatedString = request.getParameter("ownerRelated");
			ownerRelated = Boolean.valueOf(ownerRelatedString);
			String ordId = request.getParameter("orderId");
			orderId = Integer.valueOf(ordId);
			String custId = request.getParameter("customerId");
			customerId = Integer.valueOf(custId);
			paymentInfoList = customerService.getAllPaymentInfo(customerId);
			return SUCCESS;
		}
		
		public String makePayment() throws Exception{
	        
			String ownerRelatedString = request.getParameter("ownerRelated");
			ownerRelated = Boolean.valueOf(ownerRelatedString);
			String ordId = request.getParameter("orderId");
			orderId = Integer.valueOf(ordId);
			String custId = request.getParameter("customerId");
			customerId = Integer.valueOf(custId);
			String payStId = request.getParameter("payId");
			int paymentId = Integer.valueOf(payStId);
			int statusId = 0;
			for(Status st : referenceData.getStatuses()){
				if(st.getStatusName().equals(DartConstants.ORDERREADYDELIVERY)){
					statusId = st.getStatusId();
				}
			}
			orderService.updateOrderPaymentInfo(orderId,paymentId,statusId);
			return SUCCESS;
		}
		
		public String loadSearchOrders() throws Exception{
			response.setHeader("Cache-Control", "no-store");
	        response.setHeader("Pragma", "no-cache");
	        response.setDateHeader("Expires", 0);
	        
			orderStatus = "";
			statusList = referenceData.getStatuses();
			Status st = new Status();
			st.setStatusId(-1);
			st.setStatusName("All");
			
			if(!(statusList.contains(st))){
				statusList.add(st);
			}
						
			orderDate = "01/01/2014";
			ownerRelated = true;
			allOrders = new ArrayList<Order>();
			searchCustomer = "";
			customersList = "";
			List<Customer> allCustomers = customerService.getAllCustomers();
			int i = 1;
			int len = allCustomers.size();
			for( Customer ct : allCustomers){
				if( i == (len)){
					customersList += ct.getCustomerName();
				}
				else{
					customersList += (ct.getCustomerName() + ",");
				}
				i = i+1;
			}
			return SUCCESS;			
		}
		
		public String searchOrders() throws Exception{
			statusList = referenceData.getStatuses();
			String ownerRelatedString = request.getParameter("ownerRelated");
			ownerRelated = Boolean.valueOf(ownerRelatedString);
			allOrders = orderService.searchOrders(orderDate, orderStatus, searchCustomer);
			
			return SUCCESS;	
		}

		public List<Part> getPartsList() {
			return partsList;
		}

		public void setPartsList(List<Part> partsList) {
			this.partsList = partsList;
		}

		public Order getCompleteOrder() {
			return completeOrder;
		}

		public void setCompleteOrder(Order completeOrder) {
			this.completeOrder = completeOrder;
		}

		public Part getOrderPart() {
			return orderPart;
		}

		public void setOrderPart(Part orderPart) {
			this.orderPart = orderPart;
		}

		public int getCustomerId() {
			return customerId;
		}

		public void setCustomerId(int customerId) {
			this.customerId = customerId;
		}
		public int getOrderId() {
			return orderId;
		}

		public void setOrderId(int orderId) {
			this.orderId = orderId;
		}

		public List<Item> getItemsList() {
			return itemsList;
		}

		public void setItemsList(List<Item> itemsList) {
			this.itemsList = itemsList;
		}

		public List<Status> getStatusList() {
			return statusList;
		}

		public void setStatusList(List<Status> statusList) {
			this.statusList = statusList;
		}
		public List<Status> getPartialStatusList() {
			return partialStatusList;
		}

		public void setPartialStatusList(List<Status> partialStatusList) {
			this.partialStatusList = partialStatusList;
		}

		public CustomerService getCustomerService() {
			return customerService;
		}

		public void setCustomerService(CustomerService customerService) {
			this.customerService = customerService;
		}
		public List<PaymentInfo> getPaymentInfoList() {
			return paymentInfoList;
		}

		public void setPaymentInfoList(List<PaymentInfo> paymentInfoList) {
			this.paymentInfoList = paymentInfoList;
		}

		public Boolean getOwnerRelated() {
			return ownerRelated;
		}

		public void setOwnerRelated(Boolean ownerRelated) {
			this.ownerRelated = ownerRelated;
		}

		public String getOrderStatus() {
			return orderStatus;
		}

		public void setOrderStatus(String orderStatus) {
			this.orderStatus = orderStatus;
		}

		public String getOrderDate() {
			return orderDate;
		}

		public void setOrderDate(String orderDate) {
			this.orderDate = orderDate;
		}

		public List<Order> getAllOrders() {
			return allOrders;
		}

		public void setAllOrders(List<Order> allOrders) {
			this.allOrders = allOrders;
		}

		public String getCustomersList() {
			return customersList;
		}

		public void setCustomersList(String customersList) {
			this.customersList = customersList;
		}

		public String getSearchCustomer() {
			return searchCustomer;
		}

		public void setSearchCustomer(String searchCustomer) {
			this.searchCustomer = searchCustomer;
		}
}
