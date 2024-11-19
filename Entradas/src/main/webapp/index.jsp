<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <title>Entradas</title>
</head>
<body>
    <%
        Integer entradasDisponibles = (Integer) application.getAttribute("entradasDisponibles");
        if (entradasDisponibles == null) {
            entradasDisponibles = 100;
            application.setAttribute("entradasDisponibles", entradasDisponibles);
        }
    %>

    <h1>Venta de entradas</h1>
    <form action="ServletEntradas" method="GET">
        <input type="text" name="name" placeholder="Nombre de sesion" required>
        <input type="number" name="number" placeholder="Número de entradas" min="1" max="100" required>
        <input type="submit" value="Acceder">
    </form>

    <p>Entradas disponibles: <%= entradasDisponibles %></p>
</body>
</html>
