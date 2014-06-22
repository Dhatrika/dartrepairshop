<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DART</title>
<LINK media="screen" href="common.css" type="text/css" rel="STYLESHEET"></LINK>
<LINK media="screen" href="jquery-ui.css" type="text/css" rel="STYLESHEET"></LINK>
<script language="javascript" type="text/javascript" src="jquery-1.10.2.js"></script>
<script language="javascript" type="text/javascript" src="jquery-ui-1.10.4.js"></script>

<script type="text/javascript">
function searchOrders(){
	
	var selectedValue = $("#orderStatus option:selected").text();
	if(selectedValue == "Select"){
		alert("Please select a valid status");
		return false;
	}
	return true;
}

function openOrder(orderId){
	var url = "./loadOrder.action?orderId=" + orderId+"&ownerRelated="+ownerRelated.value;
	window.open(url,"_blank");
}

function openCustomer(custId){
	var url = "./loadProfile.action?customerId=" + custId;
	window.open(url,"_blank");
}

function setAutoComplete(){
	var catList = customersList.value; 
	var availableTags = catList.split(",");
	$( "#searchCustomer" ).autocomplete({
	      source: availableTags
	    });
}

</script>
 <script>
  $(function() {
    $( "#orderDate" ).datepicker();
  });
  </script>
</head>
<body onload="setAutoComplete();">

<form name="searchOrderForm" id="searchOrderForm" action="searchOrders.action" method="post">
<s:hidden id="ownerRelated" name="ownerRelated"/>
<s:hidden id="customersList" name="customersList"/>
<table>
	<tr> <td>Order Status</td>
	<td><s:select list="statusList" listKey="statusName" theme="simple"
													listValue="statusName" headerKey='-1' headerValue="Select"
													id="orderStatus"
													value="orderStatus"
													name="orderStatus"
													cssStyle="width:100px;">
												</s:select></td><td></td></tr>
<tr><td>Order Date On/After</td><td><s:textfield theme="simple" name='orderDate' id="orderDate"/></td></tr>
<tr><td>Customer</td><td><s:textfield theme="simple" name='searchCustomer' id="searchCustomer"/></td></tr>

</table>
<s:submit theme="simple" onclick="return searchOrders();" type="submit" value="SEARCH" name="SEARCH" cssStyle="color: #FFFFFF; font-size: 11px; text-align:center; font-weight: bold;  line-height: 23px; height: 23px; padding: 0px 10px 0px 10px; background-color: #454FA2; border: 0px; border-radius: 4px; -webkit-border-radius: 4px; -moz-border-radius: 4px; margin-right: 99px;"></s:submit>

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

</form>


</body>
</html>