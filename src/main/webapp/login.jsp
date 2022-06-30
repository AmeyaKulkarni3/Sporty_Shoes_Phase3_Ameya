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
		<h2>Login</h2>
		<form action="login" method="post">
			Email<br> <input type="email" name="email"><br> <br>
			Password<br> <input type="password" name="password"><br> <br>
			<input type="submit" value="Login">
		</form>
		<br> <a href="register.jsp">New User? Register!</a>
	</div>

</body>
</html>