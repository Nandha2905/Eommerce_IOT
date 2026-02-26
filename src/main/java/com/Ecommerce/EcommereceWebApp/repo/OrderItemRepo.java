package com.Ecommerce.EcommereceWebApp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Ecommerce.EcommereceWebApp.entity.OrderItem;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem , Long>{

    List<OrderItem> findByOrder_OrderId(Long orderId);

    List<OrderItem> findBySellerId(Long sellerId);
}
