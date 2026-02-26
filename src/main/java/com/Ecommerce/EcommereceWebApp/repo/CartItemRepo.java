package com.Ecommerce.EcommereceWebApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ecommerce.EcommereceWebApp.entity.CartItem;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem,Long>{
	
}
