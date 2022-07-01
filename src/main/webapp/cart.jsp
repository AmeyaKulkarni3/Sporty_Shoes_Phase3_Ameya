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
	<%@ include file="header.jsp"%>
	<div style="text-align: center">
		<h2>Cart</h2>
		<br>
		<%
		List<ProductDto> cart = (List<ProductDto>) session.getAttribute("cart");
		Map<Integer, Integer> orders = (Map<Integer, Integer>) session.getAttribute("orders");
		int i = 1;
		for (ProductDto dto : cart) {
			int qty = orders.get(dto.getId());
			double price = qty * dto.getPrice();
		%>
		<h3>
			Item No.<%=i%></h3>
		Product :
		<%=dto.getBrand()%>
		<%=dto.getName()%><br> Size :
		<%=dto.getSize()%>
		<br> Quantity :
		<%=qty%>
		Price :
		<%=price%>
		<%
		i++;
		}
		%>
		<br>
		<br>
		<form action="purchase-product">
			<input type="submit" value="Purchase">
		</form>
		<br>
		<form action="cancelPurchase.jsp">
			<input type="submit" value="Cancel">
		</form>
		<br>
		<br> <a href="<spring:url value='/shop-category'/>">Continue
			Shopping</a>
	</div>
</body>
</html>