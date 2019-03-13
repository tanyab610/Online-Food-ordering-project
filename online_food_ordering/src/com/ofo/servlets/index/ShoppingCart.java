package com.ofo.servlets.index;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ofo.servlets.daos.ProductDao;
import com.ofo.servlets.pojos.OrderDetails;
import com.ofo.servlets.pojos.OrderDetailsList;
import com.ofo.servlets.pojos.Products;

/**
 * Servlet implementation class ShoppingCart
 */
@WebServlet("/ShoppingCart")
public class ShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShoppingCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		OrderDetailsList od = (OrderDetailsList) session.getAttribute("od");

		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<script>");
		out.println("function submitForm() {");
		out.println("document.shoppingForm.submit();");
		out.println("}");
		out.println("</script>");
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
		out.println("<h1>order food online <span> : Food Cart</span></h1>");
		out.println("</header>");
		out.println("<br>");
		out.println("<div  class=\"form\">");
		out.println("<br>");
		out.println("<h1><a href=\"pages/CustomerLoginSuccess.jsp\">Home</a></h1>");
		out.println("<br>");
		out.println("<table width=\"90%\" border=\"1\">");
		out.println(
				"<tr><td align=\"center\"> ProductId </td><td align=\"center\"> Price</td><td align=\"center\"> Quantity</td></tr>");
		
		
		if(od == null) {
			out.println("<tr>");
			out.println("<td colspan=\"4\">No Items found in cart</td>");
			out.println("</tr>");
			
		}
		else {
			ArrayList<OrderDetails> listOrderDetails = od.get(); 
		for (OrderDetails orderDetail : listOrderDetails) {
		
			out.println("<tr>");
			out.println("<td>" + orderDetail.getProductId() + "</td>");
			out.println("<td>" + orderDetail.getPrice() + "</td>");
			out.println("<td>" + orderDetail.getQuantity() + "</td>");
			out.println("</tr>");
		}
		}
		out.println("</table>");
		out.println("<form id=\"closeShopping\" action=\"/online_food_ordering/SaveCart\" method=\"post\">");
		out.println("<br> <input class=\"buttom\" name=\"submit\" id=\"submit\" tabindex=\"1\" value=\"Place Order!\" type=\"submit\">");
		out.println("</form>");
		out.println("<br>");
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

}
