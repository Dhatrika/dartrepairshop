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
function validate(){
	
	var selectedValue = $("#completeOrderItemId option:selected").text();
	if(selectedValue == "Select"){
		alert("Please Select an Item to be Repaired");
		return false;
	}
	var partselectedValue = $("#orderPartPartId option:selected").text();
	if(partselectedValue == "Select"){
		alert("Please Select Part of the Item to be Repaired");
		return false;
	}
	if($("#completeOrderIsPrior").prop( "checked" ) == true){
		if(!(confirm("Priority orders cost an extra 5 bucks, Are you sure you want to continue?")))
		{
			return false;
		}
	}
	return true;
}
</script>
</head>
<body>

<form name="addOrderForm" id="addOrderForm" action="saveOrder.action" method="post">
<s:hidden id="customerId" name="customerId"/>

<table>

<tr>
	<td>Item Repaired</td>
	<td><s:select list="itemsList" listKey="itemId" theme="simple"
													listValue="itemName" headerKey='-1' headerValue="Select"
													id="completeOrderItemId"
													value="completeOrder.itemId"
													name="completeOrder.itemId"
													cssStyle="width:100px;">
												</s:select></td>

</tr>
<tr>
	<td>Order Parts</td>
	<td><s:select list="partsList" listKey="partId" theme="simple"
													listValue="partName" headerKey='-1' headerValue="Select"
													id="orderPartPartId"
													value="orderPart.partId"
													name="orderPart.partId"
													cssStyle="width:100px;">
												</s:select></td>

</tr>

<tr>
	<td>IsPriority?</td>
	<td><s:checkbox theme='simple' name="completeOrder.isPrior" id="completeOrderIsPrior"/></td>
</tr>

</table>

<br>

<s:submit theme="simple" onclick="return validate();" type="submit" value="SAVE" name="SAVE" cssStyle="color: #FFFFFF; font-size: 11px; text-align:center; font-weight: bold;  line-height: 23px; height: 23px; padding: 0px 10px 0px 10px; background-color: #454FA2; border: 0px; border-radius: 4px; -webkit-border-radius: 4px; -moz-border-radius: 4px; margin-right: 99px;"></s:submit>

</form>
</body>
</html>