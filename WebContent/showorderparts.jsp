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
var statusIdkey = 0;
function validate(){
	
	var selectedValue = $("#orderStatusId option:selected").text();
	if(selectedValue == "Select"){
		alert("Please select a valid status");
		return false;
	}
	return true;
}
function makePayment(orderId, customerId){
	var url = "./loadPayment.action?customerId=" + customerId + "&orderId="+orderId;
	window.open(url,"_self");
} 
function openCustomer(custId){
	var url = "./loadProfile.action?customerId=" + custId;
	window.open(url,"_blank");
}
function onstatuschange(){
	var selectedValue = $("#completeOrderPaidInfo").val();
	if(selectedValue == "Yes")
	{
		alert("Status cannot be changed as the payment is already done");
		$("#orderStatusId").get(0).selectedIndex = statusIdkey;
	}
}

function setStatusIdValue(){
	statusIdkey = $("#orderStatusId option:selected").index();
}
</script>
</head>
<body onload="setStatusIdValue();">

<form name="showOrderForm" id="showOrderForm" action="saveCustomerExistingOrder.action" method="post">
<s:hidden id="ownerRelated" name="ownerRelated"/>
<s:hidden id="completeOrderPaidInfo" name="completeOrder.paidInfo"/>
<s:hidden id="completeOrderStatusName" name="completeOrder.statusName"/>
<table>
<tr><td>OrderId</td><td><s:property value="completeOrder.orderId"/></td> <td></td> </tr>
<tr><td>Customer</td>  <td><a id="openCustomer" href="javascript:openCustomer(<s:property value="completeOrder.customerId"/>);"><s:property
			value="completeOrder.customerName" /></a></td> <td></td>  </tr>
	<tr>	<td>Item Repaired</td> <td><s:property value="completeOrder.itemName"/></td> <td></td> </tr>

		<tr> <td>Order Date</td> <td><s:property value="completeOrder.orderDate"/></td> <td></td> </tr>

<s:if test="ownerRelated">

	<tr> <td>Order Status</td>
	<td><s:select list="statusList" listKey="statusId" theme="simple"
													listValue="statusName" headerKey='-1' headerValue="Select"
													id="orderStatusId"
													value="completeOrder.statusId"
													name="completeOrder.statusId"
													cssStyle="width:100px;" onchange="onstatuschange();">
												</s:select></td><td></td></tr>

</s:if>

<s:else>

<s:if test="completeOrder.statusName == 'Placed' || completeOrder.statusName == 'Cancelled'">
	<tr> <td>Order Status</td>
	<td><s:select list="partialStatusList" listKey="statusId" theme="simple"
													listValue="statusName" headerKey='-1' headerValue="Select"
													id="orderStatusId"
													value="completeOrder.statusId"
													name="completeOrder.statusId"
													cssStyle="width:100px;">
												</s:select></td><td></td></tr>
</s:if>
<s:else>
<tr> <td>Order Status</td> <td><s:property value="completeOrder.statusName"/></td> <td></td></tr>
</s:else>

</s:else>




		
<s:if test="ownerRelated">
<tr> <td>Order Comments</td>  <td><s:textfield theme="simple" name='completeOrder.comments' id="comments"/></td><td></td> </tr>
</s:if>
<s:else>
<tr> <td>Order Comments</td> <td><s:property value="completeOrder.comments"/></td><td></td> </tr>
</s:else>
		
		<tr> <td>IsPriority?</td> <td><s:property value="completeOrder.priority"/></td> <td></td></tr>
		<tr> <td>Cost</td> <td><s:property value="completeOrder.actualCost"/></td> <td></td></tr>
<s:if test="completeOrder.paidInfo == 'Yes'">
		<tr> <td>IsPaid?</td> <td><s:property value="completeOrder.paidInfo"/>&nbsp;&nbsp;&nbsp;Payment Information:&nbsp;<s:property value="completeOrder.paymentInfo"/></td><td></td> </tr>
</s:if>
<s:else>
		<tr> <td>IsPaid?</td> <td><s:property value="completeOrder.paidInfo"/>&nbsp;<s:if test="completeOrder.statusName == 'Payment Pending'"><a id="makePayment" href="javascript:makePayment(<s:property value="completeOrder.orderId"/>,<s:property value="completeOrder.customerId"/>);">Make Payment</a></s:if></td><td></td> </tr>
</s:else>

<tr> </tr>
<tr> </tr>
</table>
<br>
<table>
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
<br>

<s:if test="ownerRelated">
<s:submit theme="simple" onclick="return validate();" type="submit" value="SAVE" name="SAVE" cssStyle="color: #FFFFFF; font-size: 11px; text-align:center; font-weight: bold;  line-height: 23px; height: 23px; padding: 0px 10px 0px 10px; background-color: #454FA2; border: 0px; border-radius: 4px; -webkit-border-radius: 4px; -moz-border-radius: 4px; margin-right: 99px;"></s:submit>
</s:if>
<s:else>

<s:if test="completeOrder.statusName == 'Placed' || completeOrder.statusName == 'Cancelled'">
<s:submit theme="simple" onclick="return validate();" type="submit" value="SAVE" name="SAVE" cssStyle="color: #FFFFFF; font-size: 11px; text-align:center; font-weight: bold;  line-height: 23px; height: 23px; padding: 0px 10px 0px 10px; background-color: #454FA2; border: 0px; border-radius: 4px; -webkit-border-radius: 4px; -moz-border-radius: 4px; margin-right: 99px;"></s:submit>
</s:if>

</s:else>
</form>
</body>
</html>