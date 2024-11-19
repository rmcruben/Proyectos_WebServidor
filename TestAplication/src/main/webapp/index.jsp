<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestor de Cuestionarios</title>
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
        form {
            margin-top: 30px;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 15px 30px;
            margin: 10px;
            border: none;
            cursor: pointer;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h1>Bienvenido al Gestor de Cuestionarios</h1>
    <form action="ServletTest" method="POST">
        <input type="submit" name="action" value="resolverCuestionario"> <!-- Acción para resolver -->
        <input type="submit" name="action" value="crearCuestionario"> <!-- Acción para crear -->
    </form>
</body>
</html>
