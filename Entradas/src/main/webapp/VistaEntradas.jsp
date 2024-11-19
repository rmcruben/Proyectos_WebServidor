<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
    // Recupera el nombre de la sesión
    	String name = (String) session.getAttribute("name");
    	Integer num = (Integer) request.getAttribute("num");
    	Integer entradasDisponibles = (Integer) request.getAttribute("entradasDisponibles");
    	Boolean success = (Boolean) request.getAttribute("success");

    // Mostrar el nombre de usuario
    	String nombreUsuario = (name != null) ? name : "invitado";

    	if (num != null) {
        	if (success != null && success) { // Verifica si la venta fue exitosa
	%>
            <h2>¡Gracias por tu solicitud, <%= nombreUsuario %>!</h2>
            <p>Has solicitado <%= num %> entradas.</p>
            <p>¡Disfruta del evento!</p>
	<%
        	} else {
	%>
            	<h2>Lo siento, <%= nombreUsuario %>.</h2>
            	<p>No se ha podido completar tu solicitud, ya que has pedido <%= num %> entradas, tan solo hay <%= entradasDisponibles %> disponibles.</p>
	<%
        		}
    		} else {
	%>
        <h2>Error</h2>
        <p>Lo siento, pero parece que hubo un problema con tu solicitud.</p>
	<%
    	}
	%>
</body>
</html>