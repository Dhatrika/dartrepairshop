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
<script language="javascript" type="text/javascript" src="util.js"></script>
<script type="text/javascript">

function saveCustomerInfo(){
	if(saveCustomerForm.phoneNumber.value == '' || !IsNumeric(saveCustomerForm.phoneNumber.value)){
		alert('Please enter valid Phone Number, should contain only numeric values');
		return false;
	}
	if(saveCustomerForm.customerTitle.value == ''){
		alert('Please enter valid title');
		return false;
	}
	if(saveCustomerForm.customerName.value == ''){
		alert('Please enter valid Name');
		return false;
	}
	if(saveCustomerForm.custEmailAddress.value == ''){
		alert('Please enter valid Email');
		return false;
	}
	var newEmailAddr = saveCustomerForm.custEmailAddress.value;
	if(!(newEmailAddr.indexOf('@') >= 0)) {
		alert('Please enter a valid new email address in the format i.e. xyx@hotmail.com or xyz@email.com');
		return false;
	}
	if(saveCustomerForm.street.value == ''){
		alert('Please enter valid street');
		return false;
	}
	if(saveCustomerForm.city.value == ''){
		alert('Please enter valid city');
		return false;
	}
	if(saveCustomerForm.state.value == ''){
		alert('Please enter valid state');
		return false;
	}
	if(saveCustomerForm.postalCode.value == ''  || !IsNumeric(saveCustomerForm.phoneNumber.value)){
		alert('Please enter valid postalCode, should contain only numeric values');
		return false;
	}
	if(saveCustomerForm.bankName.value == ''){
		alert('Please enter valid Bank Name');
		return false;
	}
	if(saveCustomerForm.accountNumber.value == ''  || !IsNumeric(saveCustomerForm.accountNumber.value)){
		alert('Please enter valid Account Number, should contain only numeric values');
		return false;
	}
	if(saveCustomerForm.cardNumber.value == ''  || !IsNumeric(saveCustomerForm.cardNumber.value)){
		alert('Please enter valid Card Number, should contain only numeric values');
		return false;
	}
	return true;
}

function addOrder(){
	var url = "./addOrder.action?customerId=" + customerId.value+"&ownerRelated="+ownerRelated.value;
	window.open(url,"_blank");
}

function showAllOrders(){
	var url = "./showAllOrders.action?customerId=" + customerId.value+"&ownerRelated="+ownerRelated.value;
	window.open(url,"_blank");
}
	
</script>

</head>
<body>



<form name="saveCustomerForm" id="saveCustomerForm" action="saveCustomerInfo.action" method="post">
<s:hidden id="customerId" name="customer.customerId"/>
<s:hidden id="ownerRelated" name="ownerRelated"/>

<table>
<tr><td>Title</td><td><s:textfield theme="simple" name='customer.title' id="customerTitle"/></td></tr>
<tr><td>Name</td><td><s:textfield theme="simple" name='customer.customerName' id="customerName"/></td></tr>
<tr><td>Phone</td><td><s:textfield theme="simple" name='customer.phoneNumber' id="phoneNumber"/></td></tr>
<tr><td>Email</td><td><s:textfield theme="simple" name='customer.emailAddress' id="custEmailAddress"/></td></tr>

</table>

<table>

<tr><td><h1>Address Details:</h1></td><td></td></tr>
<tr><td>Street</td><td><s:textfield theme="simple" name='customer.addr.street' id="street"/></td></tr>
<tr><td>City</td><td><s:textfield theme="simple" name='customer.addr.city' id="city"/></td></tr>
<tr><td>State</td><td><s:textfield theme="simple" name='customer.addr.state' id="state"/></td></tr>
<tr><td>PostalCode</td><td><s:textfield theme="simple" name='customer.addr.postalCode' id="postalCode"/></td></tr>

</table>

<table>

<tr><td><h1>Phone Details:</h1></td><td></td></tr>
<tr><td>Bank Name</td><td><s:textfield theme="simple" name='customer.payInfo.bankName' id="bankName"/></td></tr>
<tr><td>Account No</td><td><s:textfield theme="simple" name='customer.payInfo.accountNumber' id="accountNumber"/></td></tr>
<tr><td>Card No</td><td><s:textfield theme="simple" name='customer.payInfo.cardNumber' id="cardNumber"/></td></tr>

</table>

<br>

<s:submit theme="simple" onclick="return saveCustomerInfo();" type="submit" value="SAVE" name="SAVE" cssStyle="color: #FFFFFF; font-size: 11px; text-align:center; font-weight: bold;  line-height: 23px; height: 23px; padding: 0px 10px 0px 10px; background-color: #454FA2; border: 0px; border-radius: 4px; -webkit-border-radius: 4px; -moz-border-radius: 4px; margin-right: 99px;"></s:submit>

</form>
<br>
<br>

<s:if test = "ownerRelated">
<table>
<tr><td><a id="addOrder" href="javascript:addOrder();">Place New Order</a></td></tr>
<tr><td><a id="showAllOrders" href="javascript:showAllOrders();">Show All Orders</a></td></tr>
</table>
</s:if>

</body>
</html>