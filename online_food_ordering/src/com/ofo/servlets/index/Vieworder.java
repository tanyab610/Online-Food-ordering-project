package com.ofo.servlets.index;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ofo.servlets.daos.OrderDao;
import com.ofo.servlets.pojos.Orders;
import com.ofo.servlets.pojos.Products;

/**
 * Servlet implementation class Vieworder
 */
@WebServlet("/Vieworder")
public class Vieworder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Vieworder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		
		out.println("<title>order food online</title>");
		out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=EmulateIE7; IE=EmulateIE9\">");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println(
				"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no\"/>");
		out.println(
				"<link rel=\"stylesheet\" type=\"text/css\" href=\"/online_food_ordering/resources/style.css\" media=\"all\" />");
		out.println(
				"<link rel=\"stylesheet\" type=\"text/css\" href=\"/online_food_ordering/resources/demo.css\" media=\"all\" />");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"container\">");
		out.println("<header>");
		out.println("<h1>orders <span> Displaying Orders With Date</span></h1>");
		out.println("</header>");
		out.println("<div  class=\"form\">");
		out.println("<br>");
		out.println("<h1><a href=\"pages/ShopLoginSuccess.jsp\">Home</a></h1>");
		out.println("<br>");
		
		out.println("<table width=\"90%\" border=\"1\">");
		out.println(
				"<tr><td align=\"center\">OrderId </td><td align=\"center\"> Date of Order</td><td align=\"center\"> CustomerId</td><td align=\"center\"> amount</td></tr>");
		OrderDao pDao = new OrderDao();
		ArrayList<Orders> orderList = pDao.findAll();
		for (Orders Order : orderList) {
			out.println("<tr>");
			out.println("<td>" + Order.getOrderId() + "</td>");
			out.println("<td>" + Order.getOrderDate()  + "</td>");
			out.println("<td>" + Order.getCustomerId()+ "</td>");
			out.println("<td>" + Order.getAmount()+ "</td>");
			
			out.println("</tr>");
			
		}
		out.println("</table>");
		out.println("<br>");
		out.println("</form>");
		out.println("</div>");
	}

}
