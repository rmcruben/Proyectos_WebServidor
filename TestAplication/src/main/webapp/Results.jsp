<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Resultados del Cuestionario</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            text-align: center;
        }
        h1 {
            color: #333;
            margin-top: 50px;
        }
        p {
            font-size: 18px;
        }
        a {
            text-decoration: none;
            color: #4CAF50;
            margin-top: 20px;
            display: inline-block;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h1>Resultados del Cuestionario</h1>
    <%
        int score = (int) request.getAttribute("score");
        int totalQuestions = (int) request.getAttribute("totalQuestions");
    %>
    <p>Has respondido correctamente <%= score %> de <%= totalQuestions %> preguntas.</p>
    
    <a href="index.jsp">Volver al inicio</a> 
    
</body>
</html>
