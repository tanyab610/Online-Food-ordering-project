package com.ofo.servlets.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ofo.servlets.pojos.OrderDetails;
import com.ofo.servlets.utilities.ConnectionPool;

public class OrderDetailsDao {
	public void create(OrderDetails order) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "insert into orderdetails (odid, orderid, productId,quantity,price) values(?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, order.getOdId());
			ps.setInt(2, order.getOrderId());
			ps.setInt(3, order.getProductId());
			ps.setInt(4, order.getQuantity());
			ps.setFloat(5, order.getPrice());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public void edit(OrderDetails order) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "update orderDetails set Orderid = ?, productId = ?,quantity=?,price=? where odid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, order.getOrderId());
			ps.setInt(2, order.getProductId());
			ps.setInt(3, order.getQuantity());
			ps.setFloat(4, order.getPrice());
			ps.setInt(5, order.getOdId());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to edit row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public void remove(int odid) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "delete from orderDetails where odId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, odid);
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to remove row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public OrderDetails find(int odId) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		OrderDetails order = new OrderDetails();
		try {
			String sql = "select * from orderDetails where odId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, odId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				order.setOrderId(rs.getInt("orderId"));
				order.setProductId(rs.getInt("productId"));
				order.setQuantity(rs.getInt("quantity"));
				order.setPrice(rs.getFloat("price"));
			}
		} catch (SQLException eq) {
			System.out.print("unable to find" + eq);
		} finally {
			pool.putConnection(conn);
		}
		return order;
	}
	/*
	 * public ArrayList<OrderDetails> findAll() { ConnectionPool pool =
	 * ConnectionPool.getInstance(); pool.initialize(); Connection conn =
	 * pool.getConnection(); ArrayList<OrderDetails> listorder = new
	 * ArrayList<OrderDetails>(); try { String sql =
	 * "select * from orderDetails"; PreparedStatement ps =
	 * conn.prepareStatement(sql); ResultSet rs = ps.executeQuery(); while
	 * (rs.next()) { OrderDetails order = new OrderDetails();
	 * order.setOdId(rs.getInt("odid")); order.setOrderId(rs.getInt("orderId"));
	 * order.setProductId(rs.getInt("productId"));
	 * order.setQuantity(rs.getInt("quantity"));
	 * order.setPrice(rs.getFloat("price")); listorder.add(order); } } catch
	 * (SQLException sq) { System.out.println("unable to find the record."+sq);
	 * } finally { pool.putConnection(conn); } return listorder; }
	 */

	public static void main(String args[]) {

		OrderDetails order = new OrderDetails();
		OrderDetailsDao mo = new OrderDetailsDao();
		mo.find(2);
		System.out.print(order);
		/*
		 * OrderDetails cd = new OrderDetails(); ArrayList<OrderDetails> al
		 * =cd.findAll(); for(OrderDetails order : al) {
		 * System.out.println(order); }
		 */
		/*
		 * OrderDetailsDaos md=new OrderDetailsDaos(); OrderDetails order=new
		 * OrderDetails(1,4,1,5050,1); md.edit(order);
		 */
		// OrderDetailsDaos dd=new OrderDetailsDaos();
		// dd.remove(3);
		//
		/*
		 * OrderDetailsDaos od=new OrderDetailsDaos(); OrderDetails order=new
		 * OrderDetails(1,2,1,4,50000); od.create(order);
		 */
		/*
		 * OrderDetailsDaos do= new OrderDetailsDaos(); OrderDetails order=new
		 * OrderDetails(4,4,1,5000,5);
		 */

	}
}