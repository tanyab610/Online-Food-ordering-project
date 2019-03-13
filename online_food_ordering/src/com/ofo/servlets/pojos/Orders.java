package com.ofo.servlets.pojos;

import java.util.Date;

public class Orders {
	private int orderId;
	private Date orderDate;
	private int customerId;
	private float amount;
	
	public Orders() {
		super();
		this.orderDate= new Date();
		this.orderDate = new Date();
	}

	public Orders(Date orderDate, int customerId) {
		super();
		this.orderDate = orderDate;
		this.customerId = customerId;
	}
	public Orders(float amount, int customerId, Date orderDate) {
		super();
		this.orderDate = orderDate;
		this.customerId = customerId;
		this.amount = amount;
	}	
		
	

	public Orders(Date orderDate, int customerId, float amount) {
		super();
		this.orderDate = orderDate;
		this.customerId = customerId;
		this.amount = amount;
	}

	public Orders(int orderId, Date orderDate, int customerId, float amount) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.customerId = customerId;
		this.amount = amount;
	}


	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
	



	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderDate=" + orderDate + ", customerId=" + customerId + ", amount="
				+ amount + "]";
	}

}
