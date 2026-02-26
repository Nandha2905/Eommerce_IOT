package com.Ecommerce.EcommereceWebApp.entity;



import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long cartId;
	private double total_amount;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users user;
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	private List<CartItem> cart_items;
	
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cart(Long cartId, double total_amount, Users user, List<CartItem> cart_items) {
		super();
		this.cartId = cartId;
		this.total_amount = total_amount;
		this.user = user;
		this.cart_items = cart_items;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}

	

	public Users getUserId() {
		return user;
	}

	public void setUserId(Users user) {
		this.user = user;
	}

	public List<CartItem> getCart_items() {
		return cart_items;
	}

	public void setCart_items(List<CartItem> cart_items) {
		this.cart_items = cart_items;
	}

	
	
	
}
