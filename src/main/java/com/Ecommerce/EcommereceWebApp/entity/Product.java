package com.Ecommerce.EcommereceWebApp.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false)
    private String productName;

    
    private String description;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private boolean active = true;   
    
    @Column(nullable = false)
    private Boolean deactivatedByAdmin = false;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Users user;
    
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private ProductImg image;
  
    public Product() {
    }

   






	public Product(Long productId, String productName, String description, double price, int stock, boolean active,
			Boolean deactivatedByAdmin, Category category, Users user, ProductImg image) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.active = active;
		this.deactivatedByAdmin = deactivatedByAdmin;
		this.category = category;
		this.user = user;
		this.image = image;
	}








	public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double  getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }




	public ProductImg getImage() {
		return image;
	}




	public void setImage(ProductImg image) {
		this.image = image;
	}




	public void setProductId(Long productId) {
		this.productId = productId;
	}








	public Boolean getDeactivatedByAdmin() {
		return deactivatedByAdmin;
	}








	public void setDeactivatedByAdmin(Boolean deactivatedByAdmin) {
		this.deactivatedByAdmin = deactivatedByAdmin;
	}
    
	
}
