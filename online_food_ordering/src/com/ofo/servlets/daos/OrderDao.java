package com.ofo.servlets.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ofo.servlets.pojos.OrderDetails;
import com.ofo.servlets.pojos.Orders;
import com.ofo.servlets.utilities.ConnectionPool;
import com.ofo.servlets.utilities.DateUtils;

public class OrderDao {
	public void create(Orders orders) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "insert into orders (orderDate, amount,customerId) values(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			java.sql.Date dt = new java.sql.Date(orders.getOrderDate().getTime());
			ps.setDate(1, dt);
			ps.setFloat(2, orders.getAmount());
			ps.setInt(3, orders.getCustomerId());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public void create(Orders orders, ArrayList<OrderDetails> orderList) {
		float amount = 0;
		for (OrderDetails order : orderList) {
			amount += order.getPrice() * order.getQuantity();
		}
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "insert into orders (orderDate, amount,customerId) values(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			java.sql.Date dt = new java.sql.Date(orders.getOrderDate().getTime());
			ps.setDate(1, dt);
			ps.setFloat(2, amount);
			ps.setInt(3, orders.getCustomerId());
			ps.executeUpdate();
			ResultSet generatedKeys = ps.getGeneratedKeys();
			int id = 0;
			if (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
			}
			for (OrderDetails order : orderList) {
				sql = "insert into orderdetails (orderid, productId,quantity,price) values(?,?,?,?)";
				ps = conn.prepareStatement(sql);

				ps.setInt(1, id);
				ps.setInt(2, order.getProductId());
				ps.setInt(3, order.getQuantity());
				ps.setFloat(4, order.getPrice());
				ps.executeUpdate();
			}
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public void edit(Orders orders) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "update orders set amount = ? , customerId = ? , orderDate = ? where orderId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setFloat(1, orders.getAmount());
			ps.setInt(2, orders.getCustomerId());
			java.sql.Date dt = new java.sql.Date(orders.getOrderDate().getTime());
			ps.setDate(3, dt);
			ps.setInt(4, orders.getOrderId());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to edit new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public void remove(int orderId) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "delete from orders where orderId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, orderId);
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to remove row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public Orders find(int orderId) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		Orders orders = new Orders();
		try {
			String sql = "select * from Orders where orderid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, orderId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				orders.setOrderId(orderId);
				orders.setAmount(rs.getInt("amount"));
				orders.setCustomerId(rs.getInt("customerid"));
				java.sql.Date dt = rs.getDate("orderdate");
				orders.setOrderDate(new java.util.Date(dt.getTime()));
			}
		} catch (SQLException sq) {
			System.out.println("Unable to find row." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return orders;
	}

	public ArrayList<Orders> findAll() {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<Orders> listorders = new ArrayList<Orders>();
		try {
			String sql = "select * from orders";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Orders orders = new Orders();
				orders.setOrderId(rs.getInt("orderId"));
				orders.setAmount(rs.getFloat("amount"));
				orders.setCustomerId(rs.getInt("customerId"));
				java.sql.Date dt = rs.getDate("orderDate");
				orders.setOrderDate(new java.util.Date(dt.getTime()));
				listorders.add(orders);
			}
		} catch (SQLException sq) {
			System.out.println("Unable to find row." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return listorders;
	}

	public static void main(String args[]) {

		java.util.Date dt = DateUtils.convertDate("27-10-2015");
		Orders ord = new Orders(15500, 1, dt);
		OrderDao od = new OrderDao();
		OrderDetails order0 = new OrderDetails(1, 2, 3, 4, 500);
		OrderDetails order1 = new OrderDetails(1, 2, 3, 4, 500);
		OrderDetails order2 = new OrderDetails(1, 2, 3, 4, 500);
		OrderDetails order3 = new OrderDetails(1, 2, 3, 4, 500);
		OrderDetails order4 = new OrderDetails(1, 2, 3, 4, 500);
		ArrayList<OrderDetails> odList = new ArrayList<OrderDetails>();
		odList.add(order0);
		odList.add(order1);
		odList.add(order2);
		odList.add(order3);
		odList.add(order4);
		od.create(ord, odList);

		// Orders orders = new Orders(300.0f, 9, dt);
		// OrderDaos od = new OrderDaos();
		// od.edit(orders);

		// OrderDaos od = new OrderDaos();
		// od.remove(101);

		/*
		 * OrderDaos od = new OrderDaos(); Orders orders = od.find(9);
		 * System.out.println(orders);
		 */

		/*
		 * OrderDaos od = new OrderDaos(); ArrayList<Orders> al = od.findAll();
		 * for (Orders orders : al) System.out.println(orders);
		 */

	}

}
