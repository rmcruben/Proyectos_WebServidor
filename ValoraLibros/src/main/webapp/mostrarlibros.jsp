
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="paqueteLibros.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Lista de Libros</h1>
	<%
	// Recuperamos el atributo "libros" enviado desde el servlet
	ArrayList<Libro> libros = (ArrayList<Libro>) request.getAttribute("libros");

	// Validamos que el atributo no sea nulo o vacío
	if (libros != null && !libros.isEmpty()) {
		// Usamos la clase PintaHTML para generar la tabla
		String htmlTabla = PintaHTML.crearTabla(libros);
		out.print(htmlTabla);
	} else {
	%>
	<p>No hay libros disponibles para mostrar.</p>
	<%
	}
	%>
	<br>
	<a href="ServletLibros?action=exportMongo">Exportar a MongoDB</a>
	<br>
	<a href="ServletLibros?action=exportCSV">Exportar a CSV</a>
</body>
</html>