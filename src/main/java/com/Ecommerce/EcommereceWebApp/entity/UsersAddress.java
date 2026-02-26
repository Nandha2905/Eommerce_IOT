package com.Ecommerce.EcommereceWebApp.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_Address")
public class UsersAddress {

	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	private Long addressId;
	private String street;
	private String city;
	private String state;
	private String pincode;

	 @OneToOne
	    @JoinColumn(name = "user_id", nullable = false)
	    private Users user;
	 
	public UsersAddress() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsersAddress(Long addressId, String street, String city, String state, String pincode, Users user) {
		super();
		this.addressId = addressId;
		this.street = street;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.user = user;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}


	
	
	
}
