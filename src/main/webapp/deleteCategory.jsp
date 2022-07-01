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
		<%
		int id = Integer.parseInt(String.valueOf(request.getParameter("id")));
		String name = String.valueOf(request.getParameter("name"));
		%>
		<h2>Are You Sure?</h2>
		<form action="delete-category" method="post">
			Id<br> <input type="text" name="id" value="<%=id%>" readonly="readonly"><br><br>
			Category Name<br> <input type="text" name="catName" value="<%=name%>" readonly="readonly"><br><br>
			<br> <input type="submit" value="Delete">
		</form>
		<br> <a href="/category">Back</a>
	</div>

</body>
</html>