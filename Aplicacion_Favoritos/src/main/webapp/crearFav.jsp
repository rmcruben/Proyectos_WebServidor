<%@page import="paqueteRecu.PintaHTMLRecu"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import ="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Crea tu favórito</title>
</head>
<body>
	<h1>Crea tu favórito:</h1>
<%
	ArrayList<String> temas=(ArrayList<String>) request.getAttribute("temas");
	String formulario=PintaHTMLRecu.formularioInsertarFav();
	out.print(formulario);
%>
</body>
</html>