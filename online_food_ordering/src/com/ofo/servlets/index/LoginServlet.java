package com.ofo.servlets.index;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ofo.servlets.daos.CustomersDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get request parameters for userID and password
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		System.out.println("name : " + name);
		System.out.println("name : " + email);
		System.out.println("name : " + address);

		
		if (name == null && email == null && address == null)
			response.sendRedirect("pages/LoginForm.jsp");
		int customerId = CustomersDao.verify(name, email, address);
		System.out.println("name : " + customerId);
		if (customerId != -1) {
			HttpSession session = request.getSession();
			session.setAttribute("customerId", new Integer(customerId));
			session.setAttribute("customerName", name);
			session.setAttribute("customerEmail", email);
			session.setAttribute("address", address);
			response.sendRedirect("pages/CustomerLoginSuccess.jsp");
		} else {
			response.sendRedirect("pages/LoginForm.jsp");
		}
	}

}
