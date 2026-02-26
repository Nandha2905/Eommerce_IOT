package com.Ecommerce.EcommereceWebApp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ecommerce.EcommereceWebApp.entity.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order ,Long> {

	List<Order> findByUsers_Id(Long userId);

	List<Order> findTop5ByOrderByOrderDateDesc();

}
