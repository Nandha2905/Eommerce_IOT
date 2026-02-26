package com.Ecommerce.EcommereceWebApp.serv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecommerce.EcommereceWebApp.entity.OrderItem;
import com.Ecommerce.EcommereceWebApp.repo.OrderItemRepo;

@Service
public class OrderItemServ {

	
	@Autowired
    private OrderItemRepo orderItemRepo;
	
	public List<OrderItem> getBysellerId(Long sellerid)
	{
		return orderItemRepo.findBySellerId(sellerid);
	}
	
	public List<OrderItem> getByOrderId(Long id)
	{
		return orderItemRepo.findByOrder_OrderId(id);
	}
}
