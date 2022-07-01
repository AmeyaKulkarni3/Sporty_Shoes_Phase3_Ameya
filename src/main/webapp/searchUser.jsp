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
		<h2>Search Users</h2>
		<form action="search-user-by-name" method="post">
			First Name <br> 
			<input type="text" name="fname"> <br><br>
			<input type="submit" value="Search">
		</form>
		<br><br>
		<a href="/user">Back</a>
	</div>
</body>
</html>