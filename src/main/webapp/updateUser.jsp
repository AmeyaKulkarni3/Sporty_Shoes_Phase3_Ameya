<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sporty Shoes</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div style="text-align: center">
		<h2>Update User</h2>
		<% List<String> roles = new ArrayList<>();
			roles.add("ROLE_USER");
			roles.add("ROLE_ADMIN");
		%>
		<br>
		<form action="update-user" method="post">
		First Name<br>
		<input type="text" name="fname" value="<%=request.getParameter("fname")%>">
		<br><br>
		Last Name<br>
		<input type="text" name="lname" value="<%=request.getParameter("lname")%>">
		<br><br>
		Email<br>
		<input type="email" name="email" value="<%=request.getParameter("email")%>">
		<br><br>
		Balance<br>
		<input type="text" name="balance" value="<%=request.getParameter("balance")%>">
		<br><br>
		Role<br><select name="role">
		<% for(String s : roles){
			if(s.equals(request.getParameter("role"))){
			%>
			<option value="<%=s%>" selected="selected"><%=s %></option>
			<%} %>
			<option value="<%=s%>"><%=s %></option>
		<% }%>
		</select>
		<br><br>
		<input type="hidden" name="id" value="<%=request.getParameter("id")%>">
		<input type="submit" value="Update">
		</form>
		<br> <a href="userMenu.jsp">Back</a>
	</div>

</body>-
</html>