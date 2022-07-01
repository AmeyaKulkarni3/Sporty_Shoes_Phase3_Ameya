<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ameya.sportyshoes.dto.*"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sporty Shoes</title>
</head>
<style>
table.center {
	margin-left: auto;
	margin-right: auto;
}
</style>
<body>
	<%@ include file="header.jsp"%>
	<div style="text-align: center">
		<%
		List<ProductDto> prodDtos = (List<ProductDto>) request.getAttribute("productsAdmin");
		%>
		<h2>Products in <%=prodDtos.get(0).getCategory()%> Category.</h2>
		<%
		if (prodDtos.size() == 0 || prodDtos == null) {
		%>
		<h3>No Products Available!</h3>
		<%
		} else {
		%>
		<table border=1 class="center">
			<tr>
				<th>Id</th>
				<th>Brand</th>
				<th>Name</th>
				<th>Size</th>
				<th>Category</th>
				<th>Price</th>
				<th>Quantity</th>
				<th></th>
				<th></th>
			</tr>
			<%
			for (ProductDto dto : prodDtos) {
				request.setAttribute("product", dto);
			%>
			<tr>
				<td><%=dto.getId()%></td>
				<td><%=dto.getBrand()%></td>
				<td><%=dto.getName()%></td>
				<td><%=dto.getSize()%></td>
				<td><%=dto.getCategory()%></td>
				<td><%=dto.getPrice()%></td>
				<td><%=dto.getQuantity()%></td>
				<td><a href="updateProduct.jsp?id=<%=dto.getId()%>&brand=<%=dto.getBrand()%>&name=<%=dto.getName()%>&size=<%=dto.getSize()%>&category=<%=dto.getCategory()%>
						&price=<%=dto.getPrice()%>&quantity=<%=dto.getQuantity()%>">Update</a></td>
				<td><a href="deleteProduct.jsp?id=<%=dto.getId()%>&brand=<%=dto.getBrand()%>&name=<%=dto.getName()%>&size=<%=dto.getSize()%>&category=<%=dto.getCategory()%>
						&price=<%=dto.getPrice()%>&quantity=<%=dto.getQuantity()%>">Remove</a></td>
			</tr>
			<%
			}
			%>
		</table>
		<br> <br>
		<%
		}
		%>
		<a href="/product">Back</a>
	</div>
</body>
</html>