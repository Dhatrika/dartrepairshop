<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DART</title>
<LINK media="screen" href="common.css" type="text/css" rel="STYLESHEET"></LINK>
</head>
<script>
function validate(){
	if(internalResetPasswordForm.internalPwd.value == ''){
		alert('Please enter valid password');
				return false;
	}
	return true;
}

</script>
<body>

<h3>Reset Password</h3>

<form id="internalResetPasswordForm" action="internalResetPassword.action" method="post">
<s:hidden id="userId" name="userId"/>
<table>

<tr><td>&nbsp;New Password</td></tr>
<tr><td><s:textfield theme="simple" name="internalPwd" id="internalPwd"/></td></tr> 

</table>

<br>
<s:submit theme="simple" onclick="return validate();" type="submit" value="GO" name="GO" cssStyle="color: #FFFFFF; font-size: 11px; text-align:center; font-weight: bold;  line-height: 23px; height: 23px; padding: 0px 10px 0px 10px; background-color: #454FA2; border: 0px; border-radius: 4px; -webkit-border-radius: 4px; -moz-border-radius: 4px; margin-right: 99px;"></s:submit>

</form>

</body>
</html>