<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="com.ameya.sportyshoes.dto.*" %>
	<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		List<ProductDto> products = (List<ProductDto>) session.getAttribute("products");
		int size = Integer.parseInt(request.getParameter("size"));
		List<ProductDto> dtos = new ArrayList<>();
		for(ProductDto p : products){
			if(p.getSize() == size){
				dtos.add(p);
			}
		}
		session.setAttribute("products", dtos);
		response.sendRedirect("purchaseProduct.jsp");
	%>

</body>
</html>