package com.ofo.servlets.pojos;

public class Products {
	private int productId;
	private String productName;
	private float productPrice;
	private int stockInHand;
	
	private int categoryId;
	private int shopId;


	public Products() {
		productName = new String();

	}

	public Products(String productName, float productPrice, int stockInHand,  int categoryId) {
		super();
		this.productName = productName;
		this.productPrice = productPrice;
		this.stockInHand = stockInHand;
		
		this.categoryId = categoryId;
	}
	public Products(String productName, float productPrice, int stockInHand,  int categoryId,int shopId) {
		super();
		this.productName = productName;
		this.productPrice = productPrice;
		this.stockInHand = stockInHand;
		
		this.categoryId = categoryId;
		this.shopId = shopId;
	}


	public Products(int productId, String productName, float productPrice, int stockInHand, 
			int categoryId) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.stockInHand = stockInHand;
		
		this.categoryId = categoryId;
	}
	public Products(int productId, String productName, float productPrice, int stockInHand, 
			int categoryId, int shopId) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.stockInHand = stockInHand;
		
		this.categoryId = categoryId;
		this.shopId = shopId;
	}


	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	public int getStockInHand() {
		return stockInHand;
	}

	public void setStockInHand(int stockInHand) {
		this.stockInHand = stockInHand;
	}

	

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getshopId() {
		return shopId;
	}
	public void setshopId(int shopId) {
		this.shopId = shopId;
	}

	@Override
	public String toString() {
		return "Products [productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", stockInHand=" + stockInHand +  ", categoryId=" + categoryId
				+ "shopId=" + shopId + "]";
	}
}
