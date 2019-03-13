package com.ofo.servlets.index;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ofo.servlets.daos.OrderDao;
import com.ofo.servlets.pojos.OrderDetails;
import com.ofo.servlets.pojos.OrderDetailsList;
import com.ofo.servlets.pojos.Orders;

/**
 * Servlet implementation class SaveCart
 */
@WebServlet("/SaveCart")
public class SaveCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SaveCart() {
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
		HttpSession session = request.getSession();
		OrderDetailsList orderDetailsList = (OrderDetailsList) session.getAttribute("od");
		if (orderDetailsList == null) {
			response.sendRedirect("pages/CustomerLoginSuccess.jsp");
		} else {
			Integer customerId = (Integer) session.getAttribute("customerId");
			ArrayList<OrderDetails> listOrderDetails = orderDetailsList.get();
			OrderDao orderDao = new OrderDao();
			Orders ord = new Orders(new java.util.Date(),customerId);
			orderDao.create(ord, listOrderDetails);
			session.setAttribute("od", null);
			session.removeAttribute("od");
			response.sendRedirect("pages/CustomerLoginSuccess.jsp");
		}
	}

}
