<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inicio</title>
</head>
<body>
	<form action="ServletLibros">
	<input type="text" id="usr" name="usr" placeholder="Introduce un usuario...">
	<input type="password" id="pwd" name="pwd" placeholder="Contraseña...">
	<input type="hidden" name="action" value="login">
	<input type="submit">
	</form><br>
	<a href="ServletLibros?action=ver">Valorar Libros</a>
</body>
</html>