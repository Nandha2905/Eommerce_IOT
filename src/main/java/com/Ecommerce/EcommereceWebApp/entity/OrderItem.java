package com.Ecommerce.EcommereceWebApp.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orderitem")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long ProductId;
    
    private String productName;

    private double price;

    private int quantity;
    
    private String description;
    private Long sellerId;
  
	@ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem() {}
    
    
    public Long getProductId() {
		return ProductId;
	}


	public void setProductId(Long productId) {
		ProductId = productId;
	}


	public Long getSellerId() {
  		return sellerId;
  	}

  	public void setSellerId(Long sellerId) {
  		this.sellerId = sellerId;
  	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
  
}
