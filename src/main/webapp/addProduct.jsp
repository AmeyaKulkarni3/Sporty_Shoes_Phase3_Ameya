<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="com.ameya.sportyshoes.dto.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sporty Shoes</title>
</head>
<body>
	<%
	List<CategoryDto> dtos = (List<CategoryDto>) session.getAttribute("cats");
	%>
	<%@ include file="header.jsp"%>
	<div style="text-align: center">
		<h2>Add Product</h2>
		<br>
		<form action="add-product" method="post">
			Brand<br> <input type="text" name="brand"> <br> <br>
			Product Name<br> <input type="text" name="name"> <br>
			<br> Size<br> <input type="text" name="size"> <br>
			<br> Price<br> <input type="text" name="price"><br>
			<br> Quantity<br> <input type="text" name="quantity"><br>
			<br> Category<br>
			<br> <select name="category">
				<%
				for (CategoryDto dto : dtos) {
				%>
				<option value="<%=dto.getName()%>"><%=dto.getName()%></option>
				<%
				}
				%>
			</select><br>
			<br> <input type="submit" value="Add"><br>
			<br>
		</form>
		<br> <a href="<spring:url value='/product'/>">Back</a>
	</div>

</body>
</html>