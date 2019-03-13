package com.ofo.servlets.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ofo.servlets.pojos.Customer;
import com.ofo.servlets.utilities.ConnectionPool;
import com.ofo.servlets.utilities.DateUtils;

public class CustomersDao {

	public static int verify(String name, String email, String address) {
		int id = -1;
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "select customerId from customers where customerName = ? and email = ? and address = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, address);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt("customerId");
			}
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return id;
	}

	public void create(Customer customer) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "insert into customers(customerName,email,dob,address) values(?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, customer.getCoustomerName());
			java.sql.Date dt = new java.sql.Date(customer.getDob().getTime());
			ps.setString(2, customer.getEmail());
			ps.setDate(3, dt);
			ps.setString(4, customer.getaddress());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public void edit(Customer customer) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "update customer set customerName = ?, dateOfBirth = ?,email=? ,address=?  where customerId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, customer.getCoustomerName());
			java.sql.Date dt = new java.sql.Date(customer.getDob().getTime());
			ps.setDate(2, dt);
			ps.setString(3, customer.getaddress());
			ps.setString(4, customer.getEmail());
			ps.setInt(5, customer.getCoustomerId());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public void remove(int customerId) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "delete from customer where customerId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, customerId);
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public Customer find(int customerId) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		Customer customer = new Customer();
		try {
			String sql = "select * from customers where customerid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, customerId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				customer.setCoustomerId(customerId);
				customer.setCoustomerName(rs.getString("customername"));
				customer.setEmail("email");
				java.sql.Date dt = rs.getDate("dobdate");
				customer.setaddress(rs.getString("address"));
				customer.setDob(new java.util.Date(dt.getTime()));
			}
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return customer;
	}

	public static void main(String args[]) {
//		java.util.Date dt = DateUtils.convertDate("23-10-2015");
//		Customer customer = new Customer("rinku", "rinku@gmail.com", dt);
//		CustomersDaos obj = new CustomersDaos();
//		obj.create(customer);

		// Customer customer1 = new Customer(9,"rinku",1-1-2012, "Certi@gmail");
		// CustomersDaos cd = new CustomersDaos();
		// cd.edit(customer1);
		//
		// CustomersDaos cd = new CustomersDaos();
		// cd.remove(107);

		// CustomersDaos cd = new CustomersDaos();
		// Customers customer = cd.find(102);
		// System.out.println(customer);

		// CustomersDaos cd = new CustomersDaos();
		// ArrayList<customer> al = cd.findAll();
		// for (Customer customer : al)
		// System.out.println(customer);

		//System.out.println(verify("rohit","rohit@gmail.com"));
	}
}
