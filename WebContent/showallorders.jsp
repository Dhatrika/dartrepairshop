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
	var url = "./loadOrder.action?orderId=" + orderId;
	window.open(url,"_blank");
}

</script>
</head>
<body>

<s:hidden id="customerId" name="customerId"/>

<table>
<tr>
		<td>OrderId</td>
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


</body>
</html>