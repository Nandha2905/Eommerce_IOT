package com.Ecommerce.EcommereceWebApp.entity;

import jakarta.persistence.Embeddable;


@Embeddable
public class OrderAddress {

	
	private String street;
	private String city;
	private String state;
	private String pincode;
	
	
	public OrderAddress() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderAddress(String street, String city, String state, String pincode) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
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

	
	
}
