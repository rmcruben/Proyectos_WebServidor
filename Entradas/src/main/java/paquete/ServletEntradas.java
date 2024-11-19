package paquete;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
/**
 * Servlet implementation class ServletEntradas
 */



@WebServlet("/ServletEntradas")
public class ServletEntradas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEntradas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private static int entradasDisponibles = 100;
   
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
        HttpSession session = request.getSession();
        session.setAttribute("name", name);
        int num = Integer.parseInt(request.getParameter("number"));

        if (num <= entradasDisponibles) {
            entradasDisponibles -= num; // Actualiza las entradas disponibles
            request.setAttribute("num", num);
            request.setAttribute("success", true); // Indica que la venta salio bien
        } else {
        	
            request.setAttribute("num", num);
            request.setAttribute("success", false); // Indica que no se pudo realizar la venta
        }
        getServletContext().setAttribute("entradasDisponibles", entradasDisponibles);
        
        request.setAttribute("entradasDisponibles", entradasDisponibles);
        request.getRequestDispatcher("VistaEntradas.jsp").forward(request, response); // Cambia sendRedirect por forward
    
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
