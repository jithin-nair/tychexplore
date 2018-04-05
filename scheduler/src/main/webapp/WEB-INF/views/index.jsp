<%-- 
    Document   : index
    Created on : Apr 5, 2018, 12:37:46 PM
    Author     : jithin
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello World</title>
</head>
<body>
	<h1>${msg}</h1>
	<form:form action="/" method="post">
		<input type="submit" value="run Job">
	</form:form>
</body>
</html>
