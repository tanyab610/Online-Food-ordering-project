package com.ofo.servlets.pojos;

public class OrderDetails {
	private int odId;
	private int orderId;
	private int productId;
	private int quantity;
	private float price;

	public OrderDetails() {
		super();

	}

	public OrderDetails(int orderId, int productId, int quantity, float price) {
		super();
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
	}

	public OrderDetails(int odId, int orderId, int productId, int quantity, float price) {
		super();
		this.odId = odId;
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
	}

	public int getOdId() {
		return odId;
	}

	public void setOdId(int odId) {
		this.odId = odId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "OrderDetails [odId=" + odId + ", orderId=" + orderId + ", productId=" + productId + ", quantity="
				+ quantity + ", price=" + price + "]";
	}

}