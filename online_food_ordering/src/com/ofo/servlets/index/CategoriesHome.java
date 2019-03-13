package com.ofo.servlets.index;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ofo.servlets.pojos.Categories;

/**
 * Servlet implementation class Categories
 */
@WebServlet("/CategoriesHome")
public class CategoriesHome extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoriesHome() {
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
		out.println("function del(categoryId) {");
		out.println("document.getElementById(\"categoryId\").value = categoryId;");
		out.println("document.getElementById(\"operation\").value = 'remove';");
		out.println("document.categoriesForm.submit();");
		out.println("}");
		out.println("function mod(categoryId,categoryName,categoryDetails) {");
		out.println("document.getElementById(\"categoryId\").value = categoryId;");
		out.println("document.getElementById(\"categoryName\").value = categoryName;");
		out.println("document.getElementById(\"categoryDetails\").value = categoryDetails;");
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
		out.println("<h1>order food online <span> : Categories Form</span></h1>");
		out.println("</header>");
		out.println("<div  class=\"form\">");
		out.println("<br>");
		out.println("<h1><a href=\"pages/ShopLoginSuccess.jsp\">Home</a></h1>");
		out.println("<br>");
		out.println(
				"<form id=\"categoriesForm\" name=\"categoriesForm\"  method=\"post\" action=\"CategoriesController\">");
		out.println("<p class=\"contact\">");
		out.println("<label for=\"name\">Name</label>");
		out.println("</p>");
		out.println(
				"<input id=\"categoryName\" name=\"categoryName\" placeholder=\"Name \" required=\"\" tabindex=\"1\" type=\"text\" >");
		out.println("<p class=\"contact\">");
		out.println("<label for=\"phone\">Details</label>");
		out.println("</p>");
		out.println(
				"<textarea id=\"categoryDetails\" rows=\"5\" cols=\"57\" name=\"categoryDetails\"  required=\"\" tabindex=\"2\" > </textarea><br>");
		out.println(
				"<br> <input class=\"buttom\" name=\"add\" id=\"add\" tabindex=\"3\" value=\"Add !\" type=\"submit\">");
		out.println("<input name=\"operation\" id=\"operation\"  value=\"create\" type=\"hidden\">");
		out.println("<input name=\"categoryId\" id=\"categoryId\"   type=\"hidden\">");

		out.println("</form>");
		out.println("</div>");
		out.println("<br>");
		out.println("<div  class=\"form\">");
		out.println("<br>");
		out.println("<table width=\"90%\" border=\"1\">");
		out.println(
				"<tr><td align=\"center\"> Name </td><td align=\"center\"> Details</td><td align=\"center\"> &nbsp;</td><td align=\"center\"> &nbsp;</td></tr>");
		ArrayList<Categories> catList = (ArrayList<Categories>) request.getAttribute("catList");
		for (Categories cat : catList) {
			out.println("<tr>");
			out.println("<td>" + cat.getCategoryName() + "</td>");
			out.println("<td>" + cat.getCategoryDetails() + "</td>");
			out.println(
					"<td ><input  class=\"buttom\"  name=\"edit\" id=\"edit\" value=\"Edit!\" type=\"button\" "
					+ "onclick=\"mod('"+cat.getCategoryId()+"','"+cat.getCategoryName()+"','"+cat.getCategoryDetails()+"');\"></td>");
			out.println(
					"<td ><input class=\"buttom\" name=\"delete\" id=\"delete\" value=\"Delete!\" type=\"button\" onclick=\"del('"+cat.getCategoryId()+"');\" ></td>");
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
