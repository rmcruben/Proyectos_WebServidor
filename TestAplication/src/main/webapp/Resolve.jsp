<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="testpackage.Question" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Resolver Cuestionario</title>
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
            display: inline-block;
            text-align: left;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        input[type="radio"] {
            margin: 5px;
        }
        input[type="submit"] {
            width: 100%;
            background-color: #4CAF50;
            color: white;
            padding: 10px;
            border: none;
            cursor: pointer;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        p {
            font-size: 18px;
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
    <h1>Resuelve el cuestionario</h1>

    <form action="ServletTest" method="POST">
        <%
            ArrayList<Question> questions_list = (ArrayList<Question>) request.getAttribute("questions_list");
            if (questions_list == null || questions_list.isEmpty()) {
        %>
                <p>No hay preguntas disponibles en el cuestionario. Por favor, regresa y crea algunas preguntas.</p>
        <%
            } else {
                int index = 0;  // empieza en 0 para que coincida con el índice del servlet
                for (Question question : questions_list) {
        %>
                    <p><%= (index + 1) + ". " + question.getStatement() %></p>
                    <input type="radio" name="question_<%= index %>" value="1" required> <%= question.getOption1() %><br>
                    <input type="radio" name="question_<%= index %>" value="2"> <%= question.getOption2() %><br>
                    <input type="radio" name="question_<%= index %>" value="3"> <%= question.getOption3() %><br>
                    <br>
        <%
                    index++;
                }
        %>
                <input type="hidden" name="action" value="submitAnswers">
                <input type="submit" value="Enviar Respuestas">
        <%
            }
        %>
    </form>
</body>
</html>


