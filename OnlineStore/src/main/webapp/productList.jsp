<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="storePackage.Product"%>
<%@ page import="storePackage.ProductDAO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Comprar Productos</title>
<style>
body {
	font-family: Arial, sans-serif;
	text-align: center;
	margin-top: 50px;
}

.product {
	display: inline-block;
	width: 200px;
	margin: 20px;
	padding: 15px;
	border: 1px solid #ddd;
	border-radius: 8px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
	text-align: center;
}

.product img {
	width: 100px;
	height: 100px;
}

.product h3 {
	font-size: 18px;
	margin: 10px 0;
}

.product p {
	margin: 5px 0;
}
</style>
</head>
<body>
	<h1>Lista de Productos Disponibles</h1>

	<%
	// Obtener la lista de productos con stock > 0
	ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("product_list");

	if (products == null || products.isEmpty()) {
	%>
	<p>No hay productos disponibles para comprar en este momento.</p>
	<%
	} else {
	for (Product product : products) {
	%>
	<div class="product">
		<h3><%=product.getName()%></h3>
		<p>
			Precio: €<%=product.getPrice()%></p>
		<p>
			Stock:
			<%=product.getStock()%>
			unidades
		</p>
		<a href="StoreServlet?action=buy&id=<%=product.getId()%>"> <img
			src="<%=request.getContextPath()%>/images/<%=product.getImage()%>"
			alt="<%=product.getName()%>">
		</a>
	</div>
	<%
	}
	}
	%>

</body>
</html>

