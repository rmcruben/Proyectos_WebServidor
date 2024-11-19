<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="paquete.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Contactos:</h2>
       
    <table border='1'>
    	<tr>
            <th>Nombre</th>
            <th>Telefono</th>
        </tr>
	<%
		ArrayList<Contact> contact_list = (ArrayList<Contact>) request.getAttribute("contact_list");
		for (Contact contact : contact_list) {  
    		
        	out.print("<tr><td>" + contact.getName()+"</td><td>" + contact.getTel() + "</td></tr>");
    		
    		 
		}
	%>
	</table>
    
    
    
</body>
</html>