<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="com.ameya.sportyshoes.dto.*" %>
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
	UserDto user = new UserDto();
	if(request.getAttribute("user") == null) {
		user = (UserDto) session.getAttribute("usr");
	} else {
		user = (UserDto) request.getAttribute("user"); 
		session.setAttribute("usr", user);
	}
	%>
	<h1>Welcome <%=user.getFirstName()%> !</h1>
	<br>
		<a href="<spring:url value='/shop-category'/>"> Shop For Shoes</a><br><br>
		<a href="updateUserByUser.jsp">Update Details</a><br><br>
		<form action="logout">
			<input type="submit" value="Logout">
		</form>
	</div>

</body>
</html>