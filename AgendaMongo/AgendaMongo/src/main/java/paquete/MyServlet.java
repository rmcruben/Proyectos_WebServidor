package paquete;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");

		// Si la acción es 'viewContacts', cargamos la lista de contactos
		if ("viewContacts".equals(action)) {
			ArrayList<Contact> contact_list = DataAccess.getAllContactsMongoRemoto();

			// Pasamos la lista de contactos a contactos.jsp
			request.setAttribute("contact_list", contact_list);
			request.getRequestDispatcher("ViewContacts.jsp").forward(request, response);
		} else {
			response.getWriter().append("Served at: ").append(request.getContextPath());
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
		if ("saveContacts".equals(action)) {

			// 1.REcupero datos del formulario
			String name = request.getParameter("name");
			String tel = request.getParameter("tel");
			Contact contact = new Contact(name, tel);
			// 2. Grabo contactos en el csv a traves de una clase externa
			DataAccess.registerContactMongoRemoto(contact);
			// 3.recupero contactos en el csv a traves de una clase externa

			ArrayList<Contact> contact_list = DataAccess.getAllContactsMongoRemoto();
			// Meto los contactos en un atribute y lo mando a la vista.

			request.setAttribute("contact_list", contact_list);

			// Redirigir a la página JSP
			request.getRequestDispatcher("index.jsp").forward(request, response);

			// doGet(request, response); Esta comentado para que me redirja bien.
		}
		if ("searchContacts".equals(action)) {
			String nameSearch = request.getParameter("nameSearch");

			ArrayList<Contact> serached_contacts = DataAccess.searchByNameMongoRemoto(nameSearch);

			// Pasamos la lista de contactos encontrados a ViewContacts.jsp
			request.setAttribute("serached_contacts", serached_contacts);
			request.getRequestDispatcher("SearchedContacts.jsp").forward(request, response);
		}

	}
}
