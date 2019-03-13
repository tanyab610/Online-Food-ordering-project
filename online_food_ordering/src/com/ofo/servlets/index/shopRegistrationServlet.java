package com.ofo.servlets.index;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ofo.servlets.daos.Shopdao;
import com.ofo.servlets.pojos.Shop;
import com.ofo.servlets.utilities.DateUtils;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/shopRegistrationServlet")
public class shopRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public shopRegistrationServlet() {
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
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String dob = request.getParameter("dob");
		if (name != null && email != null && dob != null) {
			java.util.Date dtDob = DateUtils.convertDate(dob);
			Shop c = new Shop(name, email, dtDob);
			Shopdao cd = new Shopdao();
			cd.create(c);
		}
		response.sendRedirect("pages/ShopLogin.jsp");
	}

}
