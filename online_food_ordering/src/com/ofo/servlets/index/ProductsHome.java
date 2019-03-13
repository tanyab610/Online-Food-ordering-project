package com.ofo.servlets.index;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ofo.servlets.daos.CategoriesDao;
import com.ofo.servlets.daos.Shopdao;
import com.ofo.servlets.pojos.Categories;
import com.ofo.servlets.pojos.Shop;
import com.ofo.servlets.pojos.Products;

/**
 * Servlet implementation class ProductsHome
 */
@WebServlet("/ProductsHome")
public class ProductsHome extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductsHome() {
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
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<script>");
		out.println("function del(productId) {");
		out.println("document.getElementById(\"productId\").value = productId;");
		out.println("document.getElementById(\"operation\").value = 'remove';");
		out.println("document.productsForm.submit();");
		out.println("}");
		out.println("function mod(productId,productName,productPrice, stockInHand, categoryId) {");
		out.println("document.getElementById(\"productId\").value = productId;");
		out.println("document.getElementById(\"productName\").value = productName;");
		out.println("document.getElementById(\"productPrice\").value = productPrice;");
		out.println("document.getElementById(\"stockInHand\").value = stockInHand;");
		
		out.println("document.getElementById(\"shopId\").value = shopId;");
		out.println("document.getElementById(\"categoryId\").value = categoryId;");

		out.println("document.getElementById(\"add\").value = 'Save!';");
		out.println("document.getElementById(\"operation\").value = 'edit';");
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
		out.println("<h1>order food online <span> : Products Form</span></h1>");
		out.println("</header>");
		out.println("<div  class=\"form\">");
		out.println("<br>");
		out.println("<h1><a href=\"pages/ShopLoginSuccess.jsp\">Home</a></h1>");
		out.println("<br>");
		out.println(
				"<form id=\"categoriesForm\" name=\"productsForm\"  method=\"post\" action=\"ProductsController\">");
		out.println("<p class=\"contact\">");
		out.println("<label for=\"name\">Name</label>");
		out.println("</p>");
		out.println(
				"<input id=\"productName\" name=\"productName\" placeholder=\"Name\" required=\"\" tabindex=\"1\" type=\"text\" >");
		
		out.println("<p class=\"contact\">");
		out.println("<label for=\"productPrice\">Price</label>");
		out.println("</p>");
		out.println(
				"<input id=\"productPrice\" name=\"productPrice\" placeholder=\"Price\" required=\"\" tabindex=\"2\" type=\"number\" >");

		out.println("<p class=\"contact\">");
		out.println("<label for=\"stockInHand\">Stock In Hand</label>");
		out.println("</p>");
		out.println(
				"<input id=\"stockInHand\" name=\"stockInHand\" placeholder=\"stockInHand\" required=\"\" tabindex=\"3\" type=\"number\" >");

		

		out.println("<p class=\"contact\">");
		out.println("<label for=\"categoryId\">Category</label>");
		out.println("</p>");

		CategoriesDao categoriesDao = new CategoriesDao();
		ArrayList<Categories> catList = categoriesDao.findAll();
		out.println("<select id=\"categoryId\" name=\"categoryId\"  tabindex=\"5\" > ");
		for (Categories cat : catList) {
			out.println("<option value=\"" + cat.getCategoryId() + "\">" + cat.getCategoryName() + "</option>");
		}
		out.println("</select><br>");
		out.println("<p class=\"contact\">");
		out.println("<label for=\"categoryId\">Shop</label>");
		out.println("</p>");

		Shopdao shopDao = new Shopdao();
		ArrayList<Shop> shopList = shopDao.findAll();
		out.println("<select id=\"shopId\" name=\"shopId\"  tabindex=\"5\" > ");
		for (Shop shop : shopList) {
			out.println("<option value=\"" + shop.getshopId() + "\">" + shop.getshopName() + "</option>");
		}
		out.println("</select><br>");
		out.println(
				"<br> <input class=\"buttom\" name=\"add\" id=\"add\" tabindex=\"3\" value=\"Add !\" type=\"submit\">");
		out.println("<input name=\"operation\" id=\"operation\"  value=\"create\" type=\"hidden\">");
		out.println("<input name=\"productId\" id=\"productId\"   type=\"hidden\">");

		out.println("</form>");
		out.println("</div>");
		out.println("<br>");
		out.println("<div  class=\"form\">");
		out.println("<br>");
		out.println("<table width=\"90%\" border=\"1\">");
		out.println(
				"<tr><td align=\"center\"> Name </td><td align=\"center\"> Price</td><td align=\"center\"> Stock In Hand</td><td align=\"center\"> &nbsp;</td><td align=\"center\"> &nbsp;</td></tr>");
		ArrayList<Products> productList = (ArrayList<Products>) request.getAttribute("productList");
		for (Products product : productList) {
			out.println("<tr>");
			out.println("<td>" + product.getProductName() + "</td>");
			out.println("<td>" + product.getProductPrice() + "</td>");
			out.println("<td>" + product.getStockInHand() + "</td>");
			
			out.println("<td ><input  class=\"buttom\"  name=\"edit\" id=\"edit\" value=\"Edit!\" type=\"button\" "
					+ "onclick=\"mod('" + product.getProductId() + "','" + product.getProductName() + "','"
					+ product.getProductPrice() + "','" + product.getStockInHand() + "','" + product.getCategoryId() + "','"+ product.getshopId() +"');\"></td>");
			out.println(
					"<td ><input class=\"buttom\" name=\"delete\" id=\"delete\" value=\"Delete!\" type=\"button\" onclick=\"del('"
							+ product.getProductId() + "');\" ></td>");
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
