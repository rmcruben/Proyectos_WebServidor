<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agenda</title>
</head>
<body>
	<h2>Guardar Contactos</h2>
	<form action = "MyServlet?action=saveContacts" method = "POST">
	<input type = "text" name = "name" placeholder = "Nombre del contacto">
	<input type = "tel" name = "tel" placeholder = "Numero de telefono">
	<input type = submit value = "Guardar">
	
	<a href="MyServlet?action=viewContacts">Ver Contactos</a><br>

	</form><br>
	<h2>Buscar Contactos</h2>
	<form action = "MyServlet?action=searchContacts" method = "POST">
	<input type = "text" name = "nameSearch" placeholder = "Buscar nombre del contacto">
	<input type = submit value = "Buscar">
	
	</form>
</body>
</html>