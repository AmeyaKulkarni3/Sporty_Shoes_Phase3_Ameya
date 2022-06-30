<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="com.ameya.sportyshoes.exception.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div style="text-align: center">
	<% ErrorMessage error = (ErrorMessage)request.getAttribute("er"); %>
		<br><h2><%= error.getErrorCode() %></h2><br>
		<h3><%= error.getMessage() %></h3>
	</div>
</body>
</html>