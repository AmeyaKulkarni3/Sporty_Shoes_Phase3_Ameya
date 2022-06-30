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
	<h2>Purchase</h2><br>
		<%
		List<CategoryDto> categories = (List<CategoryDto>) request.getAttribute("categories");
		%>
		<form action="filter-products-categories">
			<select name="category">
				<%
				for (CategoryDto dto : categories) {
				%>
				<option value="<%=dto.getName()%>"><%=dto.getName()%></option>
				<%
				}
				%>
			</select><br> <br> <input type="submit" value="Continue">
		</form>
		<br><br>
		<a href="userMenu.jsp">Back</a>
	</div>
</body>
</html>