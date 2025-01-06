<%@page import="paqueteLibros.PintaHTML"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="ServletLibros?action=insertar_libro" method="POST">
	<label for="nombre">Nombre: </label>
	<input type="text" name="nombre"><br>
	
	<label for="autor">Autor: </label>
	<input type="text" name="autor"><br>
	
	<label for="URL_portada">URL imagen: </label>
	<input type="text" name="URL_portada"><br>
	
	<label for="ISBN">ISBN: </label>
	<input type="number" name="ISBN"><br>
	
	<label for="puntuacion">Puntuacion:</label>
	<%= PintaHTML.crearDesplegablePuntuacion() %>
	
	<input type="submit" value="Enviar">
</form><br>
<a href="ServletLibros?action=importarCSV">Importar desde CSV</a>
</body>
</html>