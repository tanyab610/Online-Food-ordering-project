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
import com.ofo.servlets.daos.Shopdao;
import com.ofo.servlets.daos.ProductDao;
import com.ofo.servlets.pojos.Categories;
import com.ofo.servlets.pojos.Shop;
import com.ofo.servlets.pojos.Products;

/**
 * Servlet implementation class ProductsController
 */
@WebServlet("/ProductsController")
public class ProductsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductsController() {
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

		int productId = 0;
		if (request.getParameter("productId") != null && request.getParameter("productId").trim().length() > 0)
			productId = Integer.parseInt(request.getParameter("productId"));

		String productName = request.getParameter("productName");
		if (productName == null) {
			productName = new String();
		}

		float productPrice = 0;
		if (request.getParameter("productPrice") != null && request.getParameter("productPrice").trim().length() > 0)
			productPrice = Float.parseFloat(request.getParameter("productPrice"));

		int stockInHand = 0;
		if (request.getParameter("stockInHand") != null && request.getParameter("stockInHand").trim().length() > 0)
			stockInHand = Integer.parseInt(request.getParameter("stockInHand"));

		

		int categoryId = 0;
		if (request.getParameter("categoryId") != null && request.getParameter("categoryId").trim().length() > 0)
			categoryId = Integer.parseInt(request.getParameter("categoryId"));
		
		int shopId = 0;
		if (request.getParameter("shopId") != null && request.getParameter("shopId").trim().length() > 0)
			shopId = Integer.parseInt(request.getParameter("shopId"));

		String operation = request.getParameter("operation");
		if (operation == null) {
			operation = new String();
		}

		ProductDao productDao = new ProductDao();

		if (operation.equals("create")) {
			Products product = new Products(productId, productName, productPrice, stockInHand,
					categoryId,shopId);
			productDao.create(product);
		} else if (operation.equals("edit")) {
			Products product = new Products(productId, productName, productPrice, stockInHand,
					categoryId,shopId);
			productDao.edit(product);
		} else if (operation.equals("remove")) {
			productDao.remove(productId);
		}

		CategoriesDao categoriesDao = new CategoriesDao();
		ArrayList<Products> productList = productDao.findAll();
		request.setAttribute("productList", productList);
		ArrayList<Categories> categoryList = categoriesDao.findAll();
		request.setAttribute("catList", categoryList);
		Shopdao shopDao = new Shopdao();
		ArrayList<Shop> shopList = shopDao.findAll();
		request.setAttribute("shopList", shopList);
		RequestDispatcher rd = request.getRequestDispatcher("ProductsHome");
		rd.forward(request, response);
	}

}
