package paqueteLibros;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import com.mysql.fabric.xmlrpc.base.Data;

/**
 * Servlet implementation class ServletLibros
 */
@WebServlet("/ServletLibros")
public class ServletLibros extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletLibros() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
			{
		String action = request.getParameter("action");
		switch (action) {
		case "ver":
			
			redirectBookList(request,response);
			
			break;
		case "login":
			String usr = request.getParameter("usr");
			String pwd = request.getParameter("pwd");
			int rol = DataAccess.getRol(usr, pwd);
			manageRol(rol, response);
			break;
		case "puntuar_libro":
			//Recuperamos ISBN
			String ISBN = request.getParameter("ISBN");
			//Recuperamos puntuacion 
			String puntuacion = request.getParameter("puntuacion");
			//Se lo pasamos a Acceso a Datos y que se encargue
			DataAccess.updateReviews(ISBN,puntuacion);
			//Recuperamos de nuevo los libros y los volvemos a mostrar
			redirectBookList(request,response);
			break;
		case "exportMongo":
			DataAccess.exportMongo();
			break;
		case "insertar_libro":
			//Recupero datos libro
			String nombre_ = request.getParameter("nombre");
			String autor_ = request.getParameter("autor");
			String URL_portada_ = request.getParameter("URL_portada");
			String ISBN_ = request.getParameter("ISBN");
			String puntuacion_ = request.getParameter("puntuacion");
			
			//Creo objeto libro y se lo mandamos a DAO
			Libro l = new Libro(ISBN_,nombre_,autor_,URL_portada_,puntuacion_);
			
			DataAccess.insertarLibro(l);
			
			response.sendRedirect("insertarlibros.jsp");
			
			break;
		case "importarCSV":
			DataAccess.importarCSV();
			
			redirectBookList(request, response);
			
			break;
		case "exportCSV":
			DataAccess.exportCSV();
			break;
		}

	}
	private void redirectBookList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		//Recuperar libros
		ArrayList<Libro>libros = DataAccess.getLibros();
		System.out.println("Cantidad de libros recuperados: " + libros.size());
		// Meterlos en un atribute
		request.setAttribute("libros", libros);
		// ir a mostrarlibros.jsp
		request.getRequestDispatcher("mostrarlibros.jsp").forward(request, response);
		System.out.println("Redirección completada.");
		
	}
	private void manageRol(int rol, HttpServletResponse response) throws IOException {
		switch (rol) {
		case 0:
			response.sendRedirect("index.jsp");
			break;
		case 1:
			response.sendRedirect("insertarlibros.jsp");
			break;
		case 2:
			response.sendRedirect("insertarUsuariosLibros.jsp");
			break;

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
