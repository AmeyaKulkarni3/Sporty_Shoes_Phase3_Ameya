<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.ameya.sportyshoes.dto.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
		List<UserDto> users = (List<UserDto>) request.getAttribute("users");
		%>
		<a href="searchUser.jsp">Search User By Name</a>
		<h2>Registered Users</h2>
		<% if (users.size() == 0 || users == null) {%>
		<h3>No Users added!</h3>
		<%
		} else {
		%>
		<table border="1" class="center">
			<tr>
				<th>Id</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Balance</th>
				<th>Role</th>
				<th>Orders</th>
				<th></th>
				<th></th>
			</tr>
			<%
			for (UserDto dto : users) {
			%>
			<tr>
				<td><%=dto.getId()%></td>
				<td><%=dto.getFirstName()%></td>
				<td><%=dto.getLastName()%></td>
				<td><%=dto.getEmail()%></td>
				<td><%=dto.getBalance()%></td>
				<td><%=dto.getRoles().get(0)%></td>
				<% int id = dto.getId(); %>
				<td><a
					href='/get-user-orders?id=<%=id%>'>Orders</a></td>
				<td><a
					href="updateUser.jsp?id=<%=dto.getId()%>&fname=<%=dto.getFirstName()%>&lname=<%=dto.getLastName()%>&email=<%=dto.getEmail()%>&balance=<%=dto.getBalance()%>&role=<%=dto.getRoles().get(0)%>">Update</a></td>
				<td><a href="deleteUser.jsp?id=<%=dto.getId()%>&fname=<%=dto.getFirstName()%>&lname=<%=dto.getLastName()%>">Delete</a></td>
			</tr>
			<%
			}
			}
			%>
		</table>
		<br><br>
		<a href="adminMenu.jsp">Back</a>
	</div>
</body>
</html>