package web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import service.modules.OrderService;
import service.modules.ReferenceData;

import com.opensymphony.xwork2.ActionSupport;

import common.modules.Item;
import common.modules.Order;
import common.modules.Part;
import common.modules.Status;
import common.util.DartConstants;
import dao.modules.OrderDao;

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
				ReferenceData referenceData) {
			this.orderService = orderService;
			this.referenceData = referenceData;
		}
		
		public OrderAction(){
			
		}
		
		public String addOrder(){
			completeOrder = new Order();
			orderPart = new Part();
			String custrId = request.getParameter("customerId");
			customerId = Integer.valueOf(custrId);
			partsList = referenceData.getPartsList();
			itemsList = referenceData.getItems();
			statusList = referenceData.getStatuses();
			return SUCCESS;
		}
		
		public String loadOrder() throws Exception{
			String ordId = request.getParameter("orderId");
			orderId = Integer.valueOf(ordId);
			completeOrder = orderService.getCompleteOrder(orderId);
			return SUCCESS;
		}
		
		public String saveOrder() throws Exception{
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
		
}
