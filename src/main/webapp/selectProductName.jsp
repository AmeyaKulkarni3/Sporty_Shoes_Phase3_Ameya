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
	List<ProductDto> products = (List<ProductDto>) session.getAttribute("products");
	Set<String> names = new HashSet<>();
	for(ProductDto p : products){
		names.add(p.getName());
	}
	List<String> nameList = new ArrayList<>(names);
	%>
	<%@ include file="header.jsp"%>
	<div style="text-align: center">
		<h2>Purchase</h2>
		<br>
		<form action="filterName.jsp" method="post">
			Category<br> <input type="text" name="category"
				value="<%=products.get(0).getCategory()%>" readonly="readonly">
			<br><br> 
			Brand<br>
			<input type="text" name="brand" value="<%=products.get(0).getBrand()%>" readonly="readonly">
			 <br><br>
			 Name<br>
			 <select name="name">
			 	<%for(String s : nameList){%>
			 		<option value="<%=s%>"><%=s%></option>
			 <%} %>
			 </select><br><br>
			<input type="submit" value="Continue"><br> <br>
		</form>
		<br> <br> <a href="userMenu.jsp">Back</a>
	</div>

</body>
</html>