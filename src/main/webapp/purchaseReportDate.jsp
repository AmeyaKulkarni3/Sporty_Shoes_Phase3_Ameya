<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="java.time.*"%>
<%@ page import="com.ameya.sportyshoes.dto.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sporty Shoes</title>
<style>
table.center {
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div style="text-align: center">
		<%
		List<OrderDto> orders = (List<OrderDto>) request.getAttribute("orders");
		LocalDate date = orders.get(0).getDate();
		%>
		<h3>Purchases on <%=date%></h3>
		<%
		int i = 1;
		for (OrderDto order : orders) {
		%>
		<h4>Order No. <%=i%></h4>
			<%List<ProductDto> products = order.getProducts();%>
			<h5> Ordered By <%=order.getUserDto().getFirstName() %> <%=order.getUserDto().getLastName() %></h5>
			<%
			for(ProductDto p : products){%>
				Product : <%=p.getBrand() %> <%=p.getName() %><br>
				Size : <%=p.getSize() %><br>
				Price : <%=p.getPrice() %><br>
				Category : <%=p.getCategory() %><br><br>
			<%} i++;%>
			Total : Rs. <%=order.getTotalAmount() %>
			<hr width="20%">
		<%
		}
		%>
		<br>
		<a href="selectPurchaseReportDate.jsp">Back</a>		
	</div>
</body>
</html>