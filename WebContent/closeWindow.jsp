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
	<script language="javascript">	
	function init()
	{	
		closeWindow();
		return;
    }	
	function closeWindow() 
	{
	    if(document.all)
	    {
	    	window.open('', '_self');
		    window.close();
		    top.close();
	    }
	    else
	    {
	        window.open('', '_self', '');
	        window.close();
	        top.close();
	    }
	}
	</script>
</head>
<body onload="init()"></body>

</body>
</html>