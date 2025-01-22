package paqueteRecu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class FavServlet
 */
@WebServlet("/FavServlet")
public class FavServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FavServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {
		case "crearFav":
			try {
				ArrayList<String> temas = DAOFav.importarTematicas();

				request.setAttribute("temas", temas);

				request.getRequestDispatcher("crearFav.jsp").forward(request, response);
			} catch (Exception e) {
				response.sendRedirect("error.jsp");
			}

			break;
		case"exportHibernate": 
			ArrayList<Favorito>favs = DAOFav.getAllFavs();
			
			DAOFav.exportHibernate(favs);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
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
		String action = request.getParameter("action");
		switch (action) {
		case "crearTematica":
			try {

				String nombre = request.getParameter("nombre");
				Tematica t = new Tematica(nombre);

				DAOFav.guardarTematica(t);

				response.sendRedirect("index.jsp");
			} catch (Exception e) {
				response.sendRedirect("error.jsp");
			}

			break;
		case "insertarFav":
			try {
				String nombreFav = request.getParameter("nombre");
				String URL = request.getParameter("URL");
				String tematica = request.getParameter("tematica");

				Favorito f = new Favorito(nombreFav, URL, tematica);
				DAOFav.guardarFAV(f);

				response.sendRedirect("index.jsp");
			} catch (Exception e) {
				response.sendRedirect("error.jsp");
			}

			break;
		case "listarFavoritos":
			String tematicaBuscar=request.getParameter("tematicaBuscar");
			ArrayList<Favorito>favlist = DAOFav.listarFavoritos(tematicaBuscar);
			
			request.setAttribute("favlist", favlist);
			
			request.getRequestDispatcher("mostrarFavs.jsp").forward(request, response);
			
			break;
			

		}

	}
	/*
	 * private void redirectTemas(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { //Recuperar libros
	 * ArrayList<String>temas = DAOFav.importarTematicas();
	 * System.out.println("Cantidad de libros recuperados: " + temas.size()); //
	 * Meterlos en un atribute request.setAttribute("temas", temas); // ir a
	 * mostrarlibros.jsp
	 * request.getRequestDispatcher("crearFav.jsp").forward(request, response);
	 * System.out.println("Redirección completada.");
	 * 
	 * }
	 */
}
