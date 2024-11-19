<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>No hay Cuestionario</title>
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
            color: #555;
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
        @media (max-width: 600px) {
            h1 {
                font-size: 24px;
            }
            p {
                font-size: 16px;
            }
        }
    </style>
</head>
<body>
    <h1>No hay Cuestionario Creado</h1>
    <p>Para resolver un cuestionario, primero necesitas crear uno.</p>
    <a href="index.jsp">Volver al inicio</a><br>
    <a href="Generator.jsp">Crear Cuestionario</a>
</body>
</html>
