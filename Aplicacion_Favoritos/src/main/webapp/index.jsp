<%@page import="paqueteRecu.PintaHTMLRecu"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Aplicación Favoritos</title>
</head>
<body>
	<h3><a href="crearFav.jsp">Crear favorito</a><br></h3>
	<h3><a href="crearTema.jsp">Crear Tematica</a><br></h3><br>
	<%= PintaHTMLRecu.busquedaFormulario() %>
	
</body>
</html>