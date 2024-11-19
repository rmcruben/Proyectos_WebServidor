<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Generador de preguntas</title>
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
            margin-top: 20px;
            display: fit-content;
            text-align: left;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        label {
            font-size: 16px;
            color: #333;
        }
        input[type="text"], input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin-top: 10px;
            margin-bottom: 20px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        a {
            text-decoration: none;
            color: #4CAF50;
        }
        @media (max-width: 600px) {
            form {
                width: 100%;
                padding: 10px;
            }
        }
    </style>
</head>
<body>
    <h1>Generador de preguntas</h1>
    <form action="ServletTest?action=saveQuestion" method="POST">
        <label>Escribe el enunciado de la pregunta:</label><br>
        <input type="text" name="statement" placeholder="Enunciado" required><br><br>

        <label>Primera opción:</label><br>
        <input type="text" name="option1" placeholder="Opción 1" required>
        <input type="radio" name="correctAnswer" value="1" id="r1" required> <label for="r1">Correcta</label><br><br>

        <label>Segunda opción:</label><br>
        <input type="text" name="option2" placeholder="Opción 2" required>
        <input type="radio" name="correctAnswer" value="2" id="r2" > <label for="r2">Correcta</label><br><br>

        <label>Tercera opción:</label><br>
        <input type="text" name="option3" placeholder="Opción 3" required>
        <input type="radio" name="correctAnswer" value="3" id ="r3"><label for="r3">Correcta</label><br><br>

        <input type="submit" value="Guardar">
    </form><br>
    <a href="index.jsp">Volver al inicio</a>
</body>
</html>

