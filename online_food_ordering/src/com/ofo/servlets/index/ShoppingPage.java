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

import com.ofo.servlets.daos.CategoriesDao;
import com.ofo.servlets.daos.ProductDao;
import com.ofo.servlets.pojos.Categories;
import com.ofo.servlets.pojos.OrderDetailsList;
import com.ofo.servlets.pojos.Products;

/**
 * Servlet implementation class ShoppinPage
 */
@WebServlet("/ShoppingPage")
public class ShoppingPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShoppingPage() {
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
		if (od == null) {
			od = new OrderDetailsList();
			session.setAttribute("od", od);
		}
		int categoryId = 0;
		if (request.getParameter("categoryId") != null && request.getParameter("categoryId").trim().length() > 0)
			categoryId = Integer.parseInt(request.getParameter("categoryId"));

		if (request.getParameter("productId") != null && request.getParameter("productId").trim().length() > 0) {
			int productId = Integer.parseInt(request.getParameter("productId"));
			float price = Float.parseFloat(request.getParameter("productPrice"));
			od.create(productId, 1, price);
		}


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
		out.println("<h1>order <span> : Foodmenu</span></h1>");
		out.println("</header>");
		out.println("<div  class=\"form\">");
		out.println("<br>");
		out.println("<h1><a href=\"pages/CustomerLoginSuccess.jsp\">Home</a></h1>");
		out.println("<br>");
		out.println("<form id=\"shoppingForm\" name=\"shoppingForm\"  method=\"post\" >");

		out.println("<p class=\"contact\">");
		out.println("<label for=\"categoryId\">Category</label>");
		out.println("</p>");

		CategoriesDao categoriesDao = new CategoriesDao();
		ArrayList<Categories> catList = categoriesDao.findAll();
		out.println("<select id=\"categoryId\" name=\"categoryId\"  tabindex=\"5\" onchange=submitForm();> ");

		for (Categories cat : catList) {
			if (categoryId == 0) {
				categoryId = cat.getCategoryId();
			}
			if (categoryId == cat.getCategoryId())
				out.println("<option selected value=\"" + cat.getCategoryId() + "\">" + cat.getCategoryName()
						+ "</option>");
			else
				out.println("<option value=\"" + cat.getCategoryId() + "\">" + cat.getCategoryName() + "</option>");
		}
		out.println("</select><br>");
		out.println("</form>");
		out.println("</div>");

		out.println("<br>");
		out.println("<div  class=\"form\">");
		out.println("<br>");
		out.println("<table width=\"90%\" border=\"1\">");
		out.println(
				"<tr><td align=\"center\"> Name </td><td align=\"center\"> Price</td><td align=\"center\"> Shop</td><td align=\"center\"> &nbsp;</td></tr>");
		ProductDao pDao = new ProductDao();
		ArrayList<Products> productList = pDao.findAll(categoryId);
		for (Products product : productList) {
			out.println("<tr>");
			out.println("<td>" + product.getProductName() + "</td>");
			out.println("<td>" + product.getProductPrice() + "</td>");
			out.println("<td>" + product.getshopId() + "</td>");
			out.println("<form method=\"post\">");
			out.println("<input type=\"hidden\" name=\"productId\" value=\"" + product.getProductId() + "\">");
			out.println("<input type=\"hidden\" name=\"productPrice\" value=\"" + product.getProductPrice() + "\">");
			
			out.println(
					"<td ><input  class=\"buttom\"  name=\"edit\" id=\"edit\" value=\"Add to Cart!\" type=\"submit\"></td>");
			out.println("</form>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("<br>");
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");

	}

}
