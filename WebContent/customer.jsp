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
	
	function showAllOrders(){
		var url = "./showAllOrders.action?customerId=" + customerId.value;
		window.open(url,"_blank");
	}

	function addOrder(){
		var url = "./addOrder.action?customerId=" + customerId.value;
		window.open(url,"_blank");
	}

	function loadProfile(){
		var url = "./loadProfile.action?customerId=" + customerId.value;
		window.open(url,"_blank");
	}
	
</script>

</head>
<body>

<h3>Welcome! <s:property value='customer.customerName'/></h3>
<s:hidden id="customerId" name="customer.customerId"/>

<ul>
<li><a id="showDetails" href="javascript:loadProfile();">Edit Profile</a></li>
<li><a id="showAllOrders" href="javascript:showAllOrders();">Show Orders</a></li>
<li><a id="addOrder" href="javascript:addOrder();">Place New Order</a></li>
<li><a id="logout" href="logout.jsp">Logout</a></li>

</ul>

</body>
</html>