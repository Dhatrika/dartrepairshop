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
</head>
<body>

<table>
<tr><td>OrderId</td><td><s:property value="completeOrder.orderId"/></td> </tr>
	<tr>	<td>Item Repaired</td> <td><s:property value="completeOrder.itemName"/></td>  </tr>

		<tr> <td>Order Date</td> <td><s:property value="completeOrder.orderDate"/></td>  </tr>
		<tr> <td>Order Status</td> <td><s:property value="completeOrder.statusName"/></td> </tr>
		<tr> <td>Order Rating</td> <td><s:property value="completeOrder.ratingName"/></td>  </tr>

		<tr> <td>Order Comments</td> <td><s:property value="completeOrder.comments"/></td> </tr>
		<tr> <td>IsPriority?</td> <td><s:property value="completeOrder.priority"/></td> </tr>
		<tr> <td>Cost</td> <td><s:property value="completeOrder.actualCost"/></td> </tr>

<tr> </tr>
<tr> </tr>
<tr> <td> <h1> Order Parts are: </h1></td> </tr>
	<tr>
		<td>Part</td>
		<td>Brand</td>
		<td>Price</td>
	</tr>
<s:iterator value="completeOrder.orderParts" status="rowstatus1">
	<tr>
		<td><s:property
			value="partName" /></td>
		<td><s:property
			value="brand" /></td>
		<td><s:property
			value="price" /></td>
	</tr>
</s:iterator>


</table>
</body>
</html>