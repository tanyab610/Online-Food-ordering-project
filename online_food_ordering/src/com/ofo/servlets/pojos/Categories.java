package com.ofo.servlets.pojos;

public class Categories {
	private int categoryId;
	private String categoryName;
	private String categoryDetails;

	public Categories() {
		categoryName = new String();
		categoryDetails = new String();
	}

	public Categories(String categoryName, String categoryDetails) {
		super();
		this.categoryName = categoryName;
		this.categoryDetails = categoryDetails;
	}

	public Categories(int categoryId, String categoryName, String categoryDetails) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryDetails = categoryDetails;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDetails() {
		return categoryDetails;
	}

	public void setCategoryDetails(String categoryDetails) {
		this.categoryDetails = categoryDetails;
	}

	@Override
	public String toString() {
		return "Categories [categoryId=" + categoryId + ", categoryName=" + categoryName + ", categoryDetails="
				+ categoryDetails + "]";
	}

}
