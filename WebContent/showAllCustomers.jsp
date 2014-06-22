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

function openCustomer(customerId){
	var url = "./loadProfile.action?customerId=" + customerId + "&ownerRelated="+ ownerRelated.value;
	window.open(url,"_blank");
}

</script>
</head>
<body>

<s:hidden id="ownerRelated" name="ownerRelated"/>

<table>
<tr style="font-weight:bold;">
		<td>CustomerId</td>
		<td>Customer Name</td>
		<td>Email</td>
		<td>Phone</td>
</tr>
<s:iterator value="allCustomers" status="rowstatus1">
	<tr>
		<td><a id="openCustomer" href="javascript:openCustomer(<s:property value="customerId"/>);"><s:property
			value="customerId" /></a></td>
		<td><s:property
			value="customerName" /></td>
		<td><s:property
			value="emailAddress" /></td>
		<td><s:property
			value="phoneNumber" /></td>
	</tr>
</s:iterator>
</table>


</body>
</html>