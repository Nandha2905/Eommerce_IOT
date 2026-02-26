package com.Ecommerce.EcommereceWebApp.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Categories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long CategoryId;
	private String CategoryName;
	private String CategoryDesc;
	private boolean active;
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	@OneToMany(mappedBy="category")
	private List<Product> products;
	public Category(Long categoryId, String categoryName, String categoryDesc) {
		super();
		CategoryId = categoryId;
		CategoryName = categoryName;
		CategoryDesc = categoryDesc;
		this.active=true;
	}
	
	public Long getCategoryId() {
		return CategoryId;
	}
	public void setCategoryId(Long categoryId) {
		CategoryId = categoryId;
	}
	public String getCategoryName() {
		return CategoryName;
	}
	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}
	public String getCategoryDesc() {
		return CategoryDesc;
	}
	public void setCategoryDesc(String categoryDesc) {
		CategoryDesc = categoryDesc;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	
}
