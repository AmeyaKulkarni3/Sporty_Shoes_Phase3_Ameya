<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="java.time.*"%>
<%@ page import="com.ameya.sportyshoes.dto.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
		if(orders == null || orders.size() == 0){%>
			<h3>No Orders exist!</h3>
		<%} else {
		String category = orders.get(0).getProducts().get(0).getCategory();
		%>
		<h3>Purchases in <%=category%> Shoes Category</h3>
		<%
		int i = 1;
		for (OrderDto order : orders) {
		%>
		<h4>Order No. <%=i%></h4>
			<%List<ProductDto> products = order.getProducts();%>
			<h5> Ordered By <%=order.getUserDto().getFirstName() %> <%=order.getUserDto().getLastName() %></h5>
			Date : <%=order.getDate() %><br>
			<%
			for(ProductDto p : products){%>
				Product : <%=p.getBrand() %> <%=p.getName() %><br>
				Size : <%=p.getSize() %><br>
				Price : <%=p.getPrice() %><br><br>
			<%} i++;%>
			Total : Rs. <%=order.getTotalAmount() %>
			<hr width="20%">
		<%
		}}
		%>
		<br>
		<a href="selectPurchaseReportCategory.jsp">Back</a>		
	</div>
</body>
</html>