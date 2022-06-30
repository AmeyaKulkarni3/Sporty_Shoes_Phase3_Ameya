<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.ameya.sportyshoes.entity.*"%>
<%@ page import="com.ameya.sportyshoes.dto.*"%>
<%@ page import="com.ameya.sportyshoes.exception.*"%>

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
		UserDto user = (UserDto) session.getAttribute("user");
		%>
		<h2>
			Welcome
			<%=user.getFirstName() + " " + user.getLastName()%></h2>
		<br> <br> <a href="userMenu.jsp">Continue Shopping...</a> <br>
		<br>
		<form action="logout">
			<input type="submit" value="Logout">
		</form>
	</div>

</body>
</html>