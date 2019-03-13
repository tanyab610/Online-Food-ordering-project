package com.ofo.servlets.index;



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ofo.servlets.daos.Shopdao;

/**
 * Servlet implementation class shopServlet
 */
@WebServlet("/shopServlet")
public class shopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public shopServlet() {
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
		System.out.println("name : " + name);
		System.out.println("name : " + email);
		
		if (name == null && email == null)
			response.sendRedirect("pages/ShopLogin.jsp");
		int shopId = Shopdao.verify(name, email);
		System.out.println("name : " + shopId);
		if (shopId != -1) {
			HttpSession session = request.getSession();
			session.setAttribute("shopId", new Integer(shopId));
			session.setAttribute("shopName", name);
			session.setAttribute("shopEmail", email);
			response.sendRedirect("pages/ShopLoginSuccess.jsp");
		} else {
			response.sendRedirect("pages/ShopLogin.jsp");
		}
	}

}