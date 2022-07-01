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
		<h2>Add Category</h2>
		<br>
		<form action="add-category" method="post">
			Category Name<br> <input type="text" name="catName"> <br>
			<br>
			<input type="submit" value="Add"><br><br>
		</form>
		<a href="/category">Back</a>
	</div>

</body>
</html>