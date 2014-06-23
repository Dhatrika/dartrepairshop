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
	
	function searchOrders(){
		var url = "./loadSearchOrders.action";
		window.open(url,"_blank");
	}

	function allCustomers(){
		var url = "./showAllCustomers.action";
		window.open(url,"_blank");
	}
	function logOut(){
		var url = "./logOut.action";
		window.open(url,"_self");
	}
	function resetPassword(){
		var url = "./resetPassword.action?userId=" + ownerId.value;
		window.open(url,"_blank");
	}
	function showInventory(){
		var url = "./showInventory.action";
		window.open(url,"_blank");
	}
</script>

</head>
<body>

<h3>Welcome! <s:property value='owner.ownerName'/></h3>
<s:hidden id="ownerId" name="owner.ownerId"/>

<ul>
<li><a id="searchOrders" href="javascript:searchOrders();">Search Orders</a></li>
<li><a id="allCustomers" href="javascript:allCustomers();">Show All Customers</a></li>
<li><a id="logout" href="javascript:logOut();">Logout</a></li>
<li><a id="reset" href="javascript:resetPassword();">Reset Password</a></li>
<li><a id="showInventory" href="javascript:showInventory();">Show Inventory</a></li>

</ul>

</body>
</html>