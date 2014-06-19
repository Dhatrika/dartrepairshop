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
	if(loginForm.loginUserEmailAddress.value == ''){
		alert('Please enter valid emailAddress');
				return false;
	}
	else if(loginForm.loginUserPassword.value == ''){
		alert('Please enter valid password');
		return false;
	}
	else{
		return true;
	}
}

function validateResetPassword(){
	if(loginForm.loginUserEmailAddress.value == ''){
		alert('Please enter valid emailAddress');
				return false;
	}
	else{
		var url = "./resetPassword.action?checkEmail=" + loginForm.loginUserEmailAddress.value;
		window.open(url,"_blank");
		return true;
	}
}
</script>
<body>

<h3>Home</h3>

<form id="loginForm" action="login.action" method="post">

<table>
<tr><td>&nbsp;EmailAddress</td></tr>
<tr><td><s:textfield theme="simple" name="loginUser.emailAddress" id="loginUserEmailAddress"/></td></tr>
<tr><td>&nbsp;Password</td></tr>
<tr><td><s:password theme="simple" name="loginUser.password" id="loginUserPassword"/></td></tr> 

</table>

<br>
<s:submit theme="simple" onclick="return validate();" type="submit" value="GO" name="GO" cssStyle="color: #FFFFFF; font-size: 11px; text-align:center; font-weight: bold;  line-height: 23px; height: 23px; padding: 0px 10px 0px 10px; background-color: #454FA2; border: 0px; border-radius: 4px; -webkit-border-radius: 4px; -moz-border-radius: 4px; margin-right: 99px;"></s:submit>

</form>

<br>
<input id="reset" onclick="return validateResetPassword();" value="Reset Password" type="button" style="color: #FFFFFF; font-size: 11px; text-align:center; font-weight: bold;  line-height: 23px; height: 23px; padding: 0px 10px 0px 10px; background-color: #454FA2; border: 0px; border-radius: 4px; -webkit-border-radius: 4px; -moz-border-radius: 4px; margin-right: 99px;">

</body>
</html>