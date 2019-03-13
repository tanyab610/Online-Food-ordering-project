package com.ofo.servlets.index;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ofo.servlets.daos.CategoriesDao;
import com.ofo.servlets.pojos.Categories;

@WebServlet("/CategoriesController")
public class CategoriesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoriesController() {
		super();
		// TODO Auto-generated constructor stub
	}

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

		int categoryId = 0;
		if (request.getParameter("categoryId") != null && request.getParameter("categoryId").trim().length()>0)
			categoryId = Integer.parseInt(request.getParameter("categoryId"));
		String categoryName = request.getParameter("categoryName");
		if (categoryName == null) {
			categoryName = new String();
		}
		String categoryDetails = request.getParameter("categoryDetails");
		if (categoryDetails == null) {
			categoryDetails = new String();
		}
		String operation = request.getParameter("operation");
		if (operation == null) {
			operation = new String();
		}
		
		CategoriesDao catDao = new CategoriesDao();

		if (operation.equals("create")) {
			Categories cat = new Categories(categoryId, categoryName, categoryDetails);
			catDao.create(cat);
		} else if (operation.equals("edit")) {
			Categories cat = new Categories(categoryId, categoryName, categoryDetails);
			catDao.edit(cat);
		} else if (operation.equals("remove")) {
			catDao.remove(categoryId);
		} 

		ArrayList<Categories> catList = catDao.findAll();
		request.setAttribute("catList", catList);
		RequestDispatcher rd = request.getRequestDispatcher("CategoriesHome");
		rd.forward(request, response);
	}
}
