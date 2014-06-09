package service.modules;

import java.util.ArrayList;
import java.util.List;

import common.modules.Item;
import common.modules.Part;
import common.modules.Status;
import dao.modules.OrderDao;

public class ReferenceData {
	
	private OrderDao orderDao;

	private List<String> statusList;
	private List<String> ratingList;
	private List<String> itemList;
	private List<Part> partsList;
	private List<Status> statuses;
	private List<Item> items;
	
	public List<String> getStatusList() {
		return statusList;
	}
	public List<String> getRatingList() {
		return ratingList;
	}
	public List<String> getItemList() {
		return itemList;
	}
	public List<Part> getPartsList() {
		return partsList;
	}
	public List<Status> getStatuses() {
		return statuses;
	}
	public List<Item> getItems() {
		return items;
	}
	
	public void init() throws Exception{
		this.statusList = new ArrayList<String>();
		this.statuses = new ArrayList<Status>();
		this.ratingList = new ArrayList<String>();
		this.itemList = new ArrayList<String>();
		this.partsList = new ArrayList<Part>();
		this.items = new ArrayList<Item>();		
		this.statuses.addAll(orderDao.getAllOrderStates());		
		this.ratingList.addAll(orderDao.getAllRatings());
		this.items.addAll(orderDao.getAllItems());		
		this.partsList.addAll(orderDao.getAllRepairParts());
		for(Status st: statuses){
			statusList.add(st.getStatusName());
		}
		for(Item it: items){
			itemList.add(it.getItemName());
		}
	}
	public OrderDao getOrderDao() {
		return orderDao;
	}
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	public ReferenceData(OrderDao orderDao) {
		this.orderDao = orderDao;
	}	
	
}
