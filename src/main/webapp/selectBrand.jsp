<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="com.ameya.sportyshoes.dto.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sporty Shoes</title>
</head>
<body>
	<%
	List<ProductDto> products = (List<ProductDto>) request.getAttribute("products");
	Set<String> brands = new HashSet<>();
	for(ProductDto p : products){
		brands.add(p.getBrand());
	}
	List<String> brandList = new ArrayList<>(brands);
	session.setAttribute("products", products);
	%>
	<%@ include file="header.jsp"%>
	<div style="text-align: center">
		<h2>Purchase</h2>
		<br>
		<form action="filterBrand.jsp" method="post">
			Category<br> <input type="text" name="category"
				value="<%=products.get(0).getCategory()%>" readonly="readonly">
			<br> <br> Brand<br> <select name="brand">
				<%
				for (String s : brandList) {
				%>
				<option value="<%=s%>"><%=s%></option>
				<%
				}
				%>
			</select> <br><br>
			<input
				type="submit" value="Continue"><br> <br>
		</form>
		<br> <br> <a href="userMenu.jsp">Back</a>
	</div>

</body>
</html>