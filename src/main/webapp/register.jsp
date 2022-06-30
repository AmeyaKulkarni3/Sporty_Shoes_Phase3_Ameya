<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sporty Shoes</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div style="text-align: center">
		<h2>Please Register</h2>
		<br>
		<form action="register" method="post">
		First Name<br>
		<input type="text" name="fname">
		<br><br>
		Last Name<br>
		<input type="text" name="lname">
		<br><br>
		Email<br>
		<input type="email" name="email">
		<br><br>
		Password<br>
		<input type="password" name="password"><br><br>
		<input type="submit" value="Register">
		</form>
		<br> <a href="login.jsp">Back To Login</a>
	</div>

</body>
</html>