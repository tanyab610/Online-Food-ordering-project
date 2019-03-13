package com.ofo.servlets.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ofo.servlets.pojos.Categories;
import com.ofo.servlets.utilities.ConnectionPool;

public class CategoriesDao {

	public void create(Categories category) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "insert into categories (categoryName, categoryDetails) values(?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, category.getCategoryName());
			ps.setString(2, category.getCategoryDetails());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public void edit(Categories category) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "update categories set categoryName = ?, categoryDetails = ? where categoryid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, category.getCategoryName());
			ps.setString(2, category.getCategoryDetails());
			ps.setInt(3, category.getCategoryId());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public void remove(int categoryId) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "delete from categories where categoryId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, categoryId);
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public Categories find(int categoryId) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		Categories category = new Categories();
		try {
			String sql = "select * from categories where categoryid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, categoryId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				category.setCategoryId(categoryId);
				category.setCategoryName(rs.getString("categoryName"));
				category.setCategoryDetails(rs.getString("categoryDetails"));
			}
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return category;
	}

	public ArrayList<Categories> findAll() {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<Categories> listCategory = new ArrayList<Categories>();
		try {
			String sql = "select * from categories";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Categories category = new Categories();
				category.setCategoryId(rs.getInt("categoryId"));
				category.setCategoryName(rs.getString("categoryName"));
				category.setCategoryDetails(rs.getString("categoryDetails"));
				listCategory.add(category);
			}
		} catch (SQLException sq) {
			System.out.println("unable to find the record." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return listCategory;
	}

	public static void main(String args[]) {

		/*
		  CategoriesDao dc = new CategoriesDao(); Categories obj = new
		  Categories("electronics", "computer"); dc.create(obj);
		 */

		/*
		  Categories category = new Categories(224, "electronics", "laptop");
		  CategoriesDao cd = new CategoriesDao(); cd.edit(category);
		 */

		/*
		 * CategoriesDaos cd = new CategoriesDaos(); cd.remove(224);
		 */

		/*
		 * CategoriesDaos cd = new CategoriesDaos(); Categories category = new
		 * Categories(); cd.find(223); System.out.println(category);
		 */

		CategoriesDao cd = new CategoriesDao();
		ArrayList<Categories> al = cd.findAll();
		for (Categories category : al) {
			System.out.println(category);
		 
		}

	}
}
