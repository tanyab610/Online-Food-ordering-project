package com.ofo.servlets.pojos;

import java.util.Date;

public class Shop {
	private int shopId;
	private String shopName;
	private String email;
	private Date dob;

	public Shop() {
		super();
		this.shopName = new String();
		this.email = new String();
		this.dob = new Date();
	}

	public Shop(String shopName, String email, Date dob) {
		super();
		this.shopName = shopName;
		this.email = email;
		this.dob = dob;
	}

	public Shop(int shopId, String shopName, String email, Date dob) {
		super();
		this.shopId = shopId;
		this.shopName = shopName;
		this.email = email;
		this.dob = dob;
	}

	public int getshopId() {
		return shopId;
	}

	public void setshopId(int shopId) {
		this.shopId = shopId;
	}

	public String getshopName() {
		return shopName;
	}

	public void setshopName(String shopName) {
		this.shopName = shopName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "Shop [shopId=" + shopId + ", shopName=" + shopName + ", email=" + email + "]";
	}

}
