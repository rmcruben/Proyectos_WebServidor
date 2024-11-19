package testpackage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/ServletTest")
public class ServletTest extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletTest() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("crearCuestionario".equals(action)) {
            request.getRequestDispatcher("Generator.jsp").forward(request, response);
        } else if ("resolverCuestionario".equals(action)) {
            ArrayList<Question> questions_list = DataTest.getAllQuestions();
            
            if (questions_list.isEmpty()) {
                request.getRequestDispatcher("noQuestions.jsp").forward(request, response);
                return;
            }
            
            request.setAttribute("questions_list", questions_list);
            request.getRequestDispatcher("Resolve.jsp").forward(request, response);
            
        } else if ("saveQuestion".equals(action)) {
            String statement = request.getParameter("statement");
            String option1 = request.getParameter("option1");
            String option2 = request.getParameter("option2");
            String option3 = request.getParameter("option3");
            int correctOption = Integer.parseInt(request.getParameter("correctAnswer"));
            
            Question question = new Question(statement, option1, option2, option3, correctOption);
            DataTest.registerQuestion(question);

            ArrayList<Question> questions_list = DataTest.getAllQuestions();
            request.setAttribute("questions_list", questions_list);
            request.getRequestDispatcher("Generator.jsp").forward(request, response);
            
        } else if ("submitAnswers".equals(action)) {
            ArrayList<Question> questions_list = DataTest.getAllQuestions();
            
            if (questions_list.isEmpty()) {
                request.getRequestDispatcher("noQuestions.jsp").forward(request, response);
                return;
            }
            
            int score = 0;
            int index = 0;
            
            for (Question question : questions_list) {
                String answerParam = request.getParameter("question_" + index); // Ajustamos para indexado correcto
                
                if (answerParam != null) { // Verifica que no sea null
                    try {
                        int selectedAnswer = Integer.parseInt(answerParam);
                        if (selectedAnswer == question.getCorrectOption()) {
                            score++;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Respuesta no válida para la pregunta " + index + ": " + e.getMessage());
                    }
                }
                index++;
            }
            
            request.setAttribute("score", score);
            request.setAttribute("totalQuestions", questions_list.size());
            request.getRequestDispatcher("Results.jsp").forward(request, response);
        }
    }
}

