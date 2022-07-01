<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.ameya.sportyshoes.dto.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sporty Shoes</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div style="text-align: center">

		<%
		UserDto user = (UserDto) request.getAttribute("user");
		%>

		<h1>Purchase Success!</h1>
		Available Balance : <%=user.getBalance() %>
		<br>
		<br>
		<a href="userMenu.jsp">Back To Main Menu</a>
	</div>


</body>
</html>