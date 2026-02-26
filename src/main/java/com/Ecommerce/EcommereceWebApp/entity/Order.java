package com.Ecommerce.EcommereceWebApp.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	private  double total_amount;
	private  LocalDateTime orderDate;
	private String status;
   
	@ManyToOne
	@JoinColumn(name= "user_id")
	private Users users;

	@Embedded
	private OrderAddress order_add;
	
	 @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	    private List<OrderItem> items = new ArrayList<>();

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(Long orderId, double total_amount, LocalDateTime orderDate, String status, Users users,
			OrderAddress order_add, List<OrderItem> items) {
		super();
		this.orderId = orderId;
		this.total_amount = total_amount;
		this.orderDate = orderDate;
		this.status = status;
		this.users = users;
		this.order_add = order_add;
		this.items = items;
	}


	public void addItem(OrderItem item) {
	    this.items.add(item);   // add the item to the order's item list
	    item.setOrder(this);    // set the back-reference so OrderItem knows its parent order
	}



	public OrderAddress getOrder_add() {
		return order_add;
	}

	public void setOrder_add(OrderAddress order_add) {
		this.order_add = order_add;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId=orderId;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public double getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
}
