<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sporty Shoes</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div style="text-align: center">
		<h2>Hello Admin!</h2>
		<br>
		<a href="<spring:url value='/category'/>">Categories</a><br>
		<br>
		<a href="<spring:url value='/product'/>">Products</a><br>
		<br>
		<a href="<spring:url value='/user'/>">Users</a><br>
		<br>
		<a href="ordersHome.jsp">Orders</a><br><br>
		<form action="logout">
			<input type="submit" value="Logout">
		</form>
	</div>

</body>
</html>