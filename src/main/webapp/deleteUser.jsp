<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div style="text-align: center">
	<% String id = request.getParameter("id");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");%>
		<h3>Are you sure to remove <%=fname%> <%=lname %></h3>
		<form action="delete-user" method="post">
			<input type="hidden" name="id" value="<%=id%>">
			<input type="submit" value="Yes">
		</form>
	</div>
</body>
</html>