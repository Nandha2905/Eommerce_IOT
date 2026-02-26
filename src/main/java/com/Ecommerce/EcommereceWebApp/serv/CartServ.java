package com.Ecommerce.EcommereceWebApp.serv;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecommerce.EcommereceWebApp.entity.Cart;
import com.Ecommerce.EcommereceWebApp.entity.CartItem;
import com.Ecommerce.EcommereceWebApp.entity.Product;
import com.Ecommerce.EcommereceWebApp.entity.Users;
import com.Ecommerce.EcommereceWebApp.repo.CartItemRepo;
import com.Ecommerce.EcommereceWebApp.repo.CartRepo;
import com.Ecommerce.EcommereceWebApp.repo.ProductRepo;
import com.Ecommerce.EcommereceWebApp.repo.UserRepo;

@Service
public class CartServ {

	@Autowired
	private CartRepo cartRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private CartItemRepo cartitemRepo;
	
	
	public Cart getCart(Long userId) {
		Users user =userRepo.findById(userId).orElseThrow(() -> new RuntimeException("user"+userId+"Not found"));
	    return cartRepo.findByUser(user);
	}
	public Cart createCartForUser(Long id)
	{
		Users  u = userRepo.findById(id).orElseThrow(() -> new RuntimeException("user"+id+"Not found"));
		
		Cart exist= cartRepo.findByUser(u);
		if(exist != null)
		{
			return exist;
		}
		Cart c=new Cart();
		c.setUserId(u);
		c.setCart_items(new ArrayList<>());
		c.setTotal_amount(0.0);
		return cartRepo.save(c);
		
	}
	public Cart addtoCartItems(Long prod_id ,Long user_id,int quantity)
	{
		Cart c= createCartForUser(user_id);
		Product p=productRepo.findById(prod_id).orElseThrow(() -> new RuntimeException("Product "+prod_id+" not found"));
		
		CartItem exist = null;
		for(CartItem i: c.getCart_items())
		{
			if(i.getProduct().getProductId().equals(prod_id))
			{
				exist = i;
				break;
			}
		}
		if( exist != null)
		{
			exist.setQuantity(exist.getQuantity() + quantity);
		}
		else {
			  CartItem item =new CartItem();
			  item.setProduct(p);
			  item.setCart(c);
			  item.setQuantity(quantity);
			  item.setPrice(p.getPrice());
		       c.getCart_items().add(item);	
		       }
		double tot=0;
		for(CartItem i:c.getCart_items())
		{
			tot += i.getPrice()*i.getQuantity();
		}
		c.setTotal_amount(tot);
		return cartRepo.save(c);
	}
	public void deleteCartItem(Long prodId, Users user) {

	    
	    Cart cart = cartRepo.findByUser(user);
	    if (cart == null) {
	        throw new RuntimeException("Cart not found for user " + user.getId());
	    }

	  
	    CartItem itemToRemove = null;
	    for (CartItem item : cart.getCart_items()) {
	        if (item.getProduct().getProductId().equals(prodId)) {
	            itemToRemove = item;
	            break;
	        }
	    }

	    if (itemToRemove != null) {
	        Product product = itemToRemove.getProduct();

	        
	        product.setStock(product.getStock() + itemToRemove.getQuantity());
	        productRepo.save(product);  

	        cart.getCart_items().remove(itemToRemove);
	        cartitemRepo.delete(itemToRemove);  
	    }

	    double total = 0;
	    for (CartItem item : cart.getCart_items()) {
	        total += item.getPrice() * item.getQuantity();
	    }
	    cart.setTotal_amount(total);

	   
	    cartRepo.save(cart);
	}




}
