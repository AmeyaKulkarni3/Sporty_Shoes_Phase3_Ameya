<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="com.ameya.sportyshoes.dto.*" %>
	<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sporty Shoes</title>
</head>
<body>
	<%
		List<ProductDto> products = (List<ProductDto>) session.getAttribute("products");
		String brand = request.getParameter("brand");
		List<ProductDto> dtos = new ArrayList<>();
		for(ProductDto p : products){
			if(p.getBrand().equals(brand)){
				dtos.add(p);
			}
		}
		session.setAttribute("products", dtos);
		response.sendRedirect("selectProductName.jsp");
	%>

</body>
</html>