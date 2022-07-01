<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="java.util.*" %>
	<%@ page import="com.ameya.sportyshoes.dto.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sporty Shoes</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div style="text-align: center">
		<h2>Update Details</h2>
		<% UserDto user = (UserDto) session.getAttribute("usr");
		%>
		<br>
		<form action="update-user-by-user" method="post">
		First Name<br>
		<input type="text" name="fname" value="<%=user.getFirstName()%>">
		<br><br>
		Last Name<br>
		<input type="text" name="lname" value="<%=user.getLastName()%>">
		<br><br>
		Email<br>
		<input type="email" name="email" value="<%=user.getEmail()%>">
		<br><br>
		Balance<br>
		<input type="text" name="balance" value="<%=user.getBalance()%>">
		<br><br>
		<input type="hidden" name="id" value="<%=user.getId()%>">
		<input type="submit" value="Update">
		</form>
		<br> <a href="userMenu.jsp">Back</a>
	</div>

</body>-
</html>