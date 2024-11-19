package storePackage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/StoreServlet")
public class StoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StoreServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		if ("viewProducts".equals(action)) {
			ArrayList<Product> products = ProductDAO.getAllProducts();
			// Pasamos la lista de contactos a contactos.jsp
			request.setAttribute("product_list", products);
			request.getRequestDispatcher("productList.jsp").forward(request, response);
		}
		if ("buy".equals(action)) {

			String idStr = request.getParameter("id");
			if (idStr != null) {
				int productId = Integer.parseInt(idStr);
				ProductDAO.comprarProducto(productId);
			}
			// Recargar la lista de productos después de la compra
			ArrayList<Product> products = ProductDAO.getAllProducts();
			request.setAttribute("product_list", products);

			// Reenviar a productList.jsp con la lista actualizada
			request.getRequestDispatcher("productList.jsp").forward(request, response);
		} else {

			response.getWriter().append("Served at: ").append(request.getContextPath());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if ("insert".equals(action)) {

			String name = request.getParameter("name");
			String pr = request.getParameter("price");
			double price = Double.parseDouble(pr);
			String image = request.getParameter("image");
			String st = request.getParameter("stock");
			int stock = Integer.parseInt(st);

			Product p = new Product(name, price, image, stock);
			ProductDAO.registerProduct(p);

			response.sendRedirect("index.jsp");
		}
	}
}
