<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ameya.sportyshoes.dto.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
		List<CategoryDto> dtos = (List<CategoryDto>) request.getAttribute("categories");
		%>
		<a href="addCategory.jsp">Add Category</a><br> <br> <br>
		<h2>Categories</h2>
		<%
		if (dtos.size() == 0 || dtos == null) {
		%>
		<h3>No Categories added!</h3>
		<%
		} else {
		%>
		<table border=1 class="center">
			<tr>
				<th>Id</th>
				<th>Category Name</th>
				<th></th>
				<th></th>
			</tr>
			<%
			for (CategoryDto dto : dtos) {
			%>
			<tr>
				<td><%=dto.getId()%></td>
				<td><%=dto.getName()%></td>
				<td><a href="updateCategory.jsp?id=<%=dto.getId()%>&name=<%=dto.getName()%>">Update</a></td>
				<td><a href="deleteCategory.jsp?id=<%=dto.getId()%>&name=<%=dto.getName()%>">Remove</a></td>
			</tr>
			<%
			}
			%>
		</table>
		<br> <br>
		<%
		}
		%>
		<a href="adminMenu.jsp">Back</a>
	</div>
</body>
</html>