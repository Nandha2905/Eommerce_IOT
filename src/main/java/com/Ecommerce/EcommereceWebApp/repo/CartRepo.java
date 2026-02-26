package com.Ecommerce.EcommereceWebApp.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ecommerce.EcommereceWebApp.entity.Cart;
import com.Ecommerce.EcommereceWebApp.entity.Users;


@Repository
public interface CartRepo extends JpaRepository<Cart,Long>{


	   
	Cart findByUser(Users user);


}
