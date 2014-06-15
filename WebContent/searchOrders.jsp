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
	
	var selectedValue = $("#orderStatus option:selected").text();
	if(selectedValue == "Select"){
		alert("Please select a valid status");
		return false;
	}
	return true;
}

</script>
</head>
<body>

<form name="searchOrderForm" id="searchOrderForm" action="searchOrders.action" method="post">

<table>
	<tr> <td>Order Status</td>
	<td><s:select list="statusList" listKey="statusName" theme="simple"
													listValue="statusName" headerKey='-1' headerValue="Select"
													id="orderStatus"
													value="orderStatus"
													name="orderStatus"
													cssStyle="width:100px;">
												</s:select></td><td></td></tr>

</table>
<s:property value="resultCheck"/>
<s:submit theme="simple" onclick="return searchOrders();" type="submit" value="SEARCH" name="SEARCH" cssStyle="color: #FFFFFF; font-size: 11px; text-align:center; font-weight: bold;  line-height: 23px; height: 23px; padding: 0px 10px 0px 10px; background-color: #454FA2; border: 0px; border-radius: 4px; -webkit-border-radius: 4px; -moz-border-radius: 4px; margin-right: 99px;"></s:submit>
</form>


</body>
</html>