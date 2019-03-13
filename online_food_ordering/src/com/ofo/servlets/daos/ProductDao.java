package com.ofo.servlets.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ofo.servlets.pojos.Products;
import com.ofo.servlets.utilities.ConnectionPool;

public class ProductDao {
	public void create(Products product) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "insert into products(productName, productPrice,stockInHand,categoryId,shopId) values(?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, product.getProductName());
			ps.setFloat(2, product.getProductPrice());
			ps.setInt(3, product.getStockInHand());
			
			ps.setInt(4, product.getCategoryId());
			ps.setInt(5, product.getshopId());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public void create(ArrayList<Products> productList) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			for (Products product : productList) {
				String sql = "insert into products(productName, productPrice,stockInHand,categoryId,shopid) values(?,?,?,?,?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, product.getProductName());
				ps.setFloat(2, product.getProductPrice());
				ps.setInt(3, product.getStockInHand());
				
				ps.setInt(5, product.getCategoryId());
				ps.setInt(6, product.getshopId());
				ps.executeUpdate();
			}
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public void edit(Products product) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "update products set productName = ?, productPrice = ?,stockInHand=?,categoryId=?,shopId =? where productId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, product.getProductName());
			ps.setFloat(2, (int) product.getProductPrice());
			ps.setInt(3, product.getStockInHand());
			
			ps.setInt(5, product.getCategoryId());
			ps.setInt(6, product.getshopId());
			ps.setInt(7, product.getProductId());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to edit row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public void remove(int productId) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "delete from products where productId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, productId);
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to remove row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public Products find(int productId) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		Products product = new Products();
		try {
			String sql = "select * from products where productid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, productId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				product.setProductId(productId);
				product.setProductName(rs.getString("productname"));
				product.setProductPrice(rs.getFloat("productPrice"));
				product.setStockInHand(rs.getInt("stockInHand"));
				
				product.setCategoryId(rs.getInt("CategoryId"));
				product.setshopId(rs.getInt("shopId"));
			}
		} catch (SQLException sq) {
			System.out.println("Unable to find row." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return product;
	}

	public ArrayList<Products> findAll() {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<Products> listproduct = new ArrayList<Products>();
		try {
			String sql = "select * from products";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Products product = new Products();
				product.setProductId(rs.getInt("productId"));
				product.setProductName(rs.getString("productName"));
				product.setProductPrice(rs.getInt("productPrice"));
				product.setStockInHand(rs.getInt("stockInHand"));
				product.setCategoryId(rs.getInt("categoryId"));
				product.setshopId(rs.getInt("shopId"));
				listproduct.add(product);
			}
		} catch (SQLException sq) {
			System.out.println("Unable to find row." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return listproduct;
	}
	

	
	public ArrayList<Products> findAll(int categoryId) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<Products> listproduct = new ArrayList<Products>();
		try {
			String sql = "select * from products where categoryId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, categoryId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Products product = new Products();
				product.setProductId(rs.getInt("productId"));
				product.setProductName(rs.getString("productName"));
				product.setProductPrice(rs.getInt("productPrice"));
				product.setStockInHand(rs.getInt("stockInHand"));
				product.setCategoryId(rs.getInt("categoryId"));
				product.setshopId(rs.getInt("shopId"));
				listproduct.add(product);
			}
		} catch (SQLException sq) {
			System.out.println("Unable to find row." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return listproduct;
	}
	public static void main(String argsp[]) {

		/*ProductDao dp = new ProductDao();
		Products product0 = new Products("pavbhaji", 2000, 1, 2, 401,101);
		Products product1 = new Products("pavbhaji", 2000, 1, 2, 401,101);
		Products product2 = new Products("pavbhaji", 2000, 1, 2, 401,101);
		Products product3 = new Products("pavbhaji", 2000, 1, 2, 401,101);
		Products product4 = new Products("pavbhaji", 2000, 1, 2, 401,101);
		ArrayList<Products> pList = new ArrayList<Products>();
		pList.add(product0);
		pList.add(product1);
		pList.add(product2);
		pList.add(product3);
		pList.add(product4);
		dp.create(pList);*/

		/*
		 * ProductDaos pd=new ProductDaos(); Products product=new
		 * Products(1,"mobile",5000,4,5, 103); pd.edit(product);
		 */

		/*
		 * ProductDaos dc = new ProductDaos(); dc.remove(15);
		 */

		// ProductDaos cd = new ProductDaos();
		// Products product=new Products();
		// cd.find(2); System.out.println(product);
		//
		/*
		 * ProductDaos pd = new ProductDaos(); ArrayList<Products> al =
		 * pd.findAll(); for (Products product : al)
		 * System.out.println(product);
		 */

	}
}
