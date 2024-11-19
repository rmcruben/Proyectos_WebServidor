<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tienda Online</title>
<style>
body {
	font-family: Arial, sans-serif;
	text-align: center;
	margin-top: 50px;
}

.button {
	padding: 15px 25px;
	font-size: 16px;
	margin: 10px;
}
</style>
</head>
<body>
	<h1>Bienvenido a la Tienda Online</h1>
	<p>¿Qué te gustaría hacer?</p>
	<div>
		<a href="StoreServlet?action=viewProducts" class="button">Comprar Productos</a> <a
			href="insertProduct.jsp" class="button">Agregar Nuevo Producto</a>
	</div>
</body>

</html>