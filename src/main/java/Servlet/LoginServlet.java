package Servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDAO;
import Model.User;

@WebServlet(urlPatterns = { "", "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static long COOKIE_AGE = 30 * 24 * 60 * 60;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		HttpSession session = request.getSession();
		if (session.getAttribute("id") != null) {
			response.sendRedirect("index");
			return;
		}
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean remember = request.getParameter("remember") != null;
		User user = UserDAO.getInstance().login(username);

		if (user == null) {
			request.setAttribute("message", "Email does not match with any account");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		} else if (!password.equals(user.getPassword())) {
			request.setAttribute("message", "Password is invalid");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		} else {
			if (remember) {
				Cookie usernameCookie = new Cookie("username", username);
				Cookie passwordCookie = new Cookie("password", password);
				usernameCookie.setMaxAge((int) COOKIE_AGE);
				passwordCookie.setMaxAge((int) COOKIE_AGE);
				response.addCookie(usernameCookie);
				response.addCookie(passwordCookie);
			}

			HttpSession session = request.getSession();
			session.setAttribute("id", user.getId());
			session.setAttribute("username", user.getFullname());
			response.sendRedirect("index");
		}
	}

}
