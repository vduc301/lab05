package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDAO;
import Model.User;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("id") != null) {
			response.sendRedirect("index");
			return;
		}
		request.getRequestDispatcher("register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirm-password");
				
		if (password.length() < 6) {
			request.setAttribute("message", "Password must have at least 6 characters");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		} else if (!password.equals(confirmPassword)) {
			request.setAttribute("message", "Password and Confirm Password does not match");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		
		User user = new User(fullname, email, password);
		boolean register = UserDAO.getInstance().register(user);
		
		if (register) {
			response.sendRedirect("login");
		} else {
			request.setAttribute("message", "Email already exists");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
	}
}
