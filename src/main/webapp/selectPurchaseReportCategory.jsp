<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ameya.sportyshoes.dto.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sporty Shoes</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div style="text-align: center">
		<h3>Select Category</h3>
		<%
		List<CategoryDto> categories = (List<CategoryDto>) session.getAttribute("cats");
		%>
		<form action="orders-by-category" method="post">
			<select name="category">
				<%for(CategoryDto cat : categories){ %>
					<option value="<%=cat.getName()%>"><%=cat.getName()%></option>
				<%} %>
			</select>
			<br><br> <input type="submit" value="Generate Report">
		</form>
		<br><br>
		<a href="ordersHome.jsp">Back</a>
	</div>

</body>
</html>