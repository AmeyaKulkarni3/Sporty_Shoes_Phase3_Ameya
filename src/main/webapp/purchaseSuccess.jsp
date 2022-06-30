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
	List<ProductDto> products = (List<ProductDto>) session.getAttribute("products");
	%>
	<%@ include file="header.jsp"%>
	<div style="text-align: center">
		<h2>Purchase</h2>
		<br>
		<form action="purchase-product" method="post">
			Category<br> <input type="text" name="category"
				value="<%=products.get(0).getCategory()%>" readonly="readonly">
			<br><br> 
			Brand<br>
			<input type="text" name="brand" value="<%=products.get(0).getBrand()%>" readonly="readonly">
			 <br><br>
			 Name<br>
			<input type="text" name="name" value="<%=products.get(0).getName()%>" readonly="readonly">
			 <br><br>
			 Size<br>
			 <input type="text" name="size" value="<%=products.get(0).getSize()%>" readonly="readonly">
			 <br><br>
			 Price : <%=products.get(0).getPrice() %>
			 <br><br>
			 Enter Quantity between 1 and <%=products.get(0).getQuantity() %><br>
			 <input type="text" name="qty"><br><br>
			 <input type="hidden" name="id" value="<%=products.get(0).getId()%>">
			<input type="submit" value="Purchase"><br> <br>
		</form>
		<br> <br> <a href="userMenu.jsp">Back</a>
	</div>

</body>
</html>