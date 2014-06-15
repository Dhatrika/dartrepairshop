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
	
	var selectedValue = $("#payId option:selected").text();
	if(selectedValue == "Select"){
		alert("Please select valid option for payment");
		return false;
	}
	return true;
}
</script>
</head>
<body>

<form name="makePaymentForm" id="makePaymentForm" action="makePayment.action" method="post">

<s:hidden id="orderId" name="orderId"></s:hidden>
<s:hidden id="customerId" name="customerId"></s:hidden>
<s:hidden id="ownerRelated" name="ownerRelated"></s:hidden>
<table>
<tr><td>OrderId</td><td><s:property value="orderId" /></td> </tr>
<tr><td>Payment Option:</td>
<td><s:select list="paymentInfoList" listKey="paymentInfoId" theme="simple"
													listValue="totalInfo" headerKey='-1' headerValue="Select"
													id="payId"
													value="payId"
													name="payId"
													cssStyle="width:100px;">
												</s:select></td> </tr>
</table>

<s:submit theme="simple" onclick="return validate();" type="submit" value="PAY" name="PAY" cssStyle="color: #FFFFFF; font-size: 11px; text-align:center; font-weight: bold;  line-height: 23px; height: 23px; padding: 0px 10px 0px 10px; background-color: #454FA2; border: 0px; border-radius: 4px; -webkit-border-radius: 4px; -moz-border-radius: 4px; margin-right: 99px;"></s:submit>

</form>

</body>
</html>