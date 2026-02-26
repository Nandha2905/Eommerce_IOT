package com.Ecommerce.EcommereceWebApp.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id ;
	 private String name;
	 private String email;
	 private String password;
	 private String Phone_Number;
	 private String role;

	    @Column(nullable = false)
	    private boolean active = true;  
	 
	   @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	    private UsersAddress usersAddress;
	   
	   @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	   private List<Product> products;
	 
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Users(Long id, String name, String email, String password, String phone_Number, String role,
			UsersAddress usersAddress,List<Product> products) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		Phone_Number = phone_Number;
		this.role = role;
		this.usersAddress = usersAddress;
		this.products = products;
		 this.active = true;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone_Number() {
		return Phone_Number;
	}
	public void setPhone_Number(String phone_Number) {
		Phone_Number = phone_Number;
	}
	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UsersAddress getUsersAddress() {
		return usersAddress;
	}

	public void setUsersAddress(UsersAddress usersAddress) {
		this.usersAddress = usersAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	
	 
	 
	 
      
		
	 
}
