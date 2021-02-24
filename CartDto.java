package jp.co.internous.crocus.model.domain.dto;

import java.sql.Timestamp;

public class CartDto {
		
	private int id;
	private String imageFullPath;
	private String productName;
	private int price;
	private int productCount;
	private int subtotal;
	private Timestamp createdAt;
	private Timestamp updatedAt;
		
	public int getId() {
		return id;
	}
		
	public void setId(int id) {
		this.id = id;
	}
		
	public String getImageFullPath() {
		return imageFullPath;
	}
		
	public void setImageFullPath(String imageFullPath) {
		this.imageFullPath = imageFullPath;
	}
		
	public String getProductName() {
		return productName;
	}
		
	public void setProductName(String productName) {
		this.productName = productName;
	}
		
	public int price() {
		return price;
	}
		
	public void price(int price) {
		this.price = price;
	}
		
	public int getProductCount() {
		return productCount;
	}
		
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
		
	public int subtotal() {
		return subtotal;
	}
		
	public void subtotal(int subtotal) {
		this.subtotal = subtotal;
	}
		
	public Timestamp getCreatedAt() {
		return createdAt;
	}
		
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
		
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
		
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

}
