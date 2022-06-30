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
<body>
	<%
	Map<Integer, Integer> orders = new HashMap<>();
	if (session.getAttribute("orders") != null) {
		orders = (HashMap<Integer, Integer>) session.getAttribute("orders");
	}
	Integer id = Integer.parseInt(request.getParameter("id"));
	Integer quantity = Integer.parseInt(request.getParameter("qty"));
	orders.put(id, quantity);
	session.setAttribute("orders", orders);
	String category = request.getParameter("category");
	String brand = request.getParameter("brand");
	String name = request.getParameter("name");
	int size = Integer.parseInt(request.getParameter("size"));
	int qty = Integer.parseInt(request.getParameter("qty"));
	double price = Double.parseDouble(request.getParameter("price"));
	ProductDto p = new ProductDto();
	p.setCategory(category);
	p.setBrand(brand);
	p.setName(name);
	p.setQuantity(qty);
	p.setSize(size);
	p.setPrice(price);
	p.setId(id);
	List<ProductDto> cart = (List<ProductDto>) session.getAttribute("cart");
	if(cart == null){
		cart = new ArrayList<>();
	}
	cart.add(p);
	session.setAttribute("products", null);
	session.setAttribute("cart", cart);
	response.sendRedirect("cart.jsp");
	%>

</body>
</html>