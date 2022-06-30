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
		<h2>Update Category</h2>
		<form action="update-category" method="post">
			Id<br> <input type="text" name="id" value="<%=id%>"
				readonly="readonly"><br>
			<br> New Category Name<br> <input type="text"
				name="catName" value="<%=name%>"><br>
			<br> <br> <input type="submit" value="Update">
		</form>
		<br>
		<a href="categoryHome.jsp">Back</a>
	</div>

</body>
</html>