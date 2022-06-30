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
	String brand = request.getParameter("brand");
	String name = request.getParameter("name");
	String size = request.getParameter("size");
	String quantity = request.getParameter("quantity");
	String category = request.getParameter("category");
	String price = request.getParameter("price");
	String id = request.getParameter("id");
	%>
	<%@ include file="header.jsp"%>
	<div style="text-align: center">
		<h2>Are You Sure?</h2>
		<br>
		<form action="delete-product" method="post">
			Id <br> <input type="text" value="<%=id%>" name="id"
				readonly="readonly"><br> <br> Brand<br> <input
				type="text" name="brand" value="<%=brand%>" readonly="readonly">
			<br> <br> Product Name<br> <input type="text"
				name="name" value="<%=name%>" readonly="readonly"> <br>
			<br> Size<br>
			<input type="text" name="size" value="<%=size%>" readonly="readonly">
			<br> <br> Price<br> <input type="text" name="price"
				value="<%=price%>" readonly="readonly"><br> <br>
			Quantity<br>
			<input type="text" name="quantity" value="<%=quantity%>"
				readonly="readonly"><br> <br> Category<br>
			<input type="text" name="category" value="<%=category%>"
				readonly="readonly"> <br> <br> <input
				type="submit" value="Delete"><br> <br>
		</form>
		<br> <a href="<spring:url value='/product'/>">Back</a>
	</div>

</body>
</html>