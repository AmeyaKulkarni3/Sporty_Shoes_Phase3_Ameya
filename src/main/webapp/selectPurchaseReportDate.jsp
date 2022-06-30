<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div style="text-align: center">
		<h3>Select Date</h3>
		<form action="orders-by-date" method="post">
		<input type="date" name="date">
		<br><br>
		<input type="submit" value="Generate Report">
		</form>
		<br><br>
		<a href="ordersHome.jsp">Back</a>
	</div>

</body>
</html>