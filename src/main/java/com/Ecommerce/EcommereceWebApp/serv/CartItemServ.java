package com.Ecommerce.EcommereceWebApp.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecommerce.EcommereceWebApp.repo.CartItemRepo;

@Service
public class CartItemServ {

	 @Autowired
	 private CartItemRepo cartItmRepo;
	 
	 
	 public void deleteCartItemById(Long id)
	 {
		 
	 }
}
