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

<s:if test='partsList.size() > 0'>	
<h1>Parts available for repair are: </h1>
<table>
<tr style="font-weight:bold;">
		<td>Part Name</td>
		<td>Brand</td>
		<td>Price</td>
</tr>
<s:iterator value="partsList" status="rowstatus1">
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
</s:if>
<s:else>
<h1>None of the parts are available.</h1>
</s:else>


</body>
</html>