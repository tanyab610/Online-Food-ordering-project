package com.ofo.servlets.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ofo.servlets.pojos.Categories;
import com.ofo.servlets.pojos.Customer;
import com.ofo.servlets.pojos.Shop;
import com.ofo.servlets.utilities.ConnectionPool;
import com.ofo.servlets.utilities.DateUtils;

public class Shopdao {

	public static int verify(String name, String email) {
		int id = -1;
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "select shopId from shop where shopName = ? and email = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt("shopId");
			}
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return id;
	}
	public void create(Shop shop) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "insert into shop(shopName,email,dob) values(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, shop.getshopName());
			java.sql.Date dt = new java.sql.Date(shop.getDob().getTime());
			ps.setString(2, shop.getEmail());
			ps.setDate(3, dt);
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}
	public void edit(Shop shop) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "update shop set shopName = ?, dateOfBirth = ?,email=?  where shopId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, shop.getshopName());
			java.sql.Date dt = new java.sql.Date(shop.getDob().getTime());
			ps.setDate(2, dt);
			ps.setString(3, shop.getEmail());
			ps.setInt(4, shop.getshopId());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}
	public void remove(int shopId) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "delete from shop where shopId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, shopId);
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public Shop find(int shopId) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		Shop shop = new Shop();
		try {
			String sql = "select * from shop where shopid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, shopId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				shop.setshopId(shopId);
				shop.setshopName(rs.getString("shopname"));
				shop.setEmail("email");
				java.sql.Date dt = rs.getDate("dobdate");
				shop.setDob(new java.util.Date(dt.getTime()));
			}
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return shop;
	}
	public ArrayList<Shop> findAll() {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<Shop> listShop = new ArrayList<Shop>();
		try {
			String sql = "select * from Shop";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Shop shop = new Shop();
				shop.setshopId(rs.getInt("shopId"));
				shop.setshopName(rs.getString("shopName"));
				shop.setEmail("email");
				java.sql.Date dt = rs.getDate("dob");
				shop.setDob(new java.util.Date(dt.getTime()));
				listShop.add(shop);
			}
		} catch (SQLException sq) {
			System.out.println("unable to find the record." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return listShop;
	}

	public static void main(String args[]) {
	//java.util.Date dt = DateUtils.convertDate("23-10-2015");
	//Shop shop = new Shop("rinku", "rinku@gmail.com", dt);
	//Shopdao obj = new Shopdao();
	//obj.create(shop);

		// Shop shop1 = new Shop(9,"rinku",1-1-2012, "Certi@gmail");
		// ShopDao cd = new ShopDao();
		// cd.edit(shop1);
		//
		// ShopDao cd = new ShopDao();
		// cd.remove(107);

		// ShopDao cd = new ShopDao();
		// Shop shop = cd.find(102);
		// System.out.println(shop);

		// ShopDao cd = new ShopDao();
		// ArrayList<Shop> al = cd.findAll();
		// for (Shop shop : al)
		// System.out.println(shop);

		//System.out.println(verify("rohit","rohit@gmail.com"));
	Shopdao cd = new Shopdao();
	ArrayList<Shop> al = cd.findAll();
	for (Shop category : al) {
		System.out.println(category);
	 
	}}
}

