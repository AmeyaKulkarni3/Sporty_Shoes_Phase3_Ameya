<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sporty Shoes</title>
</head>
<body>
	<%
		session.setAttribute("cart", null);
		session.setAttribute("product", null);
		session.setAttribute("orders", null);
		response.sendRedirect("userMenu.jsp");
	%>

</body>
</html>