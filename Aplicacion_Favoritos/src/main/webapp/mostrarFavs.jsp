<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.ArrayList"%>
<%@ page import="paqueteRecu.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Lista de Favoritos</h1>
	<a href="FavServlet?action=exportHibernate">Exportar con Hibernate</a>
	<%
	// Recuperamos el atributo "libros" enviado desde el servlet
	ArrayList<Favorito> favlist = (ArrayList<Favorito>) request.getAttribute("favlist");

	// Validamos que el atributo no sea nulo o vacío
	if (favlist != null && !favlist.isEmpty()) {
		// Usamos la clase PintaHTML para generar la tabla
		String htmlTabla = PintaHTMLRecu.crearTabla(favlist);
		out.print(htmlTabla);
	} else {
	%>
	<p>No hay favoritos disponibles para mostrar.</p>
	<%
	}
	%>
</body>
</html>