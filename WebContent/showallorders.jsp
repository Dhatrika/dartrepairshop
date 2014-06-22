<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DART</title>
<LINK media="screen" href="common.css" type="text/css" rel="STYLESHEET"></LINK>
<script language="javascript" type="text/javascript" src="jquery-1.7.1.js"></script>
<script type="text/javascript">

function openOrder(orderId){
	var url = "./loadOrder.action?orderId=" + orderId+"&ownerRelated="+ownerRelated.value;
	window.open(url,"_blank");
}

function openCustomer(custId){
	var url = "./loadProfile.action?customerId=" + custId;
	window.open(url,"_blank");
}

</script>
</head>
<body>

<s:hidden id="customerId" name="customerId"/>
<s:hidden id="ownerRelated" name="ownerRelated"/>


<s:if test='allOrders.size() > 0'>	
<table>
<tr style="font-weight:bold;">
		<td>OrderId</td>
		<td>Customer</td>
		<td>Item Repaired</td>

		<td>Order Date</td>
		<td>Order Status</td>

		<td>Order Comments</td>
		<td>IsPriority?</td>
		<td>Cost</td>
</tr>
<s:iterator value="allOrders" status="rowstatus1">
	<tr>
		<td><a id="openOrder" href="javascript:openOrder(<s:property value="orderId"/>);"><s:property
			value="orderId" /></a></td>
		<td><a id="openCustomer" href="javascript:openCustomer(<s:property value="customerId"/>);"><s:property
			value="customerName" /></a></td>
		<td><s:property
			value="itemName" /></td>

		<td><s:property
			value="orderDate" /></td>
		<td><s:property
			value="statusName" /></td>

		<td><s:property
			value="comments" /></td>
		<td><s:property
			value="priority" /></td>
		<td><s:property
			value="actualCost" /></td>
	</tr>
</s:iterator>
</table>
</s:if>
<s:else>
<h1>No orders have been placed yet.</h1>
</s:else>


</body>
</html>