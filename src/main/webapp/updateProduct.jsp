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
		<h2>Add Product</h2>
		<br>
		<form action="update-product" method="post">
			Id <br> <input type="text" value="<%=id%>" name="id"
				readonly="readonly"><br> <br> 
			Brand<br> <input type="text" name="brand" value="<%=brand%>"> <br>
			<br> 
			Product Name<br> <input type="text" name="name"
				value="<%=name%>"> <br> <br> 
			Size<br><input type="text" name="size" value="<%=size%>"> <br>
			<br> 
			Price<br> <input type="text" name="price"
				value="<%=price%>"><br> <br> 
			Quantity<br><input type="text" name="quantity" value="<%=quantity%>"><br>
			<br> Category<br> <select name="category">
				<%
				for (CategoryDto dto : dtos) {
					if (dto.getName().equals(category)) {
				%>
				<option value="<%=dto.getName()%>" selected="selected"><%=dto.getName()%></option>
				<%
				}
				%>
				<option value="<%=dto.getName()%>"><%=dto.getName()%></option>
				<%
				}
				%>
			</select><br> <br> <input type="submit" value="Update"><br>
			<br>
		</form>
		<br> <a href="<spring:url value='/product'/>">Back</a>
	</div>

</body>
</html>