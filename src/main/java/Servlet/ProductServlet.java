package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ProductDAO;
import Model.Product;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ProductServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productId = request.getParameter("id");
		ProductDAO.getInstance().remove(Integer.parseInt(productId));
		response.sendRedirect("index");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("product-name");
		String price = request.getParameter("product-price");
		
		if (name == null) {
			request.setAttribute("message", "Name can not be null");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		} else if (price == null) {
			request.setAttribute("message", "Price can not be null");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		} else {
			Product product = new Product(name, Integer.parseInt(price));
			ProductDAO.getInstance().save(product);
			response.sendRedirect("index");
		}
	}
}
