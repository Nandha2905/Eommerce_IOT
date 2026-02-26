package com.Ecommerce.EcommereceWebApp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="productImg")
public class ProductImg {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long imgId;
	private String productImgUrl;
	
	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	
	
	public ProductImg() {
	}
	public ProductImg(Long imgId, String productImgUrl, Product productId) {
		super();
		this.imgId = imgId;
		this.productImgUrl = productImgUrl;
		this.product = productId;
	}
	public Long getImgId() {
		return imgId;
	}
	public void setImgId(Long imgId) {
		this.imgId = imgId;
	}
	public String getProductImgUrl() {
		return productImgUrl;
	}
	public void setProductImgUrl(String productImgUrl) {
		this.productImgUrl = productImgUrl;
	}
	public Product getProductId() {
		return product;
	}
	public void setProductId(Product product) {
		this.product = product;
	}
	

	
}
