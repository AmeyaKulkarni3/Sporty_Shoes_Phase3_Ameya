<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.ameya.sportyshoes.dto.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div style="text-align: center">
		<%
		List<OrderDto> orders = (List<OrderDto>) request.getAttribute("orders");
		UserDto user = (UserDto) request.getAttribute("user");
		%>
		<h3><%=user.getFirstName()%>'s Orders
		</h3>
		<%
		int i = 1;
		for (OrderDto order : orders) {
		%>
		<h4>Order No. <%=i%></h4>
			Date : <%=order.getDate() %><br>
			<%List<ProductDto> products = order.getProducts();
			for(ProductDto p : products){%>
				Product : <%=p.getBrand() %> <%=p.getName() %><br>
				Size : <%=p.getSize() %><br>
				Price : <%=p.getPrice() %><br><br>
			<%} i++;%>
			Total : Rs. <%=order.getTotalAmount() %>
			<hr width="20%">
		<%
		}
		%>
		<br><br>
		<a href="/user">Back</a>
	</div>
</body>
</html>