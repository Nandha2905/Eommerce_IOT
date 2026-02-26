package com.Ecommerce.EcommereceWebApp.controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Ecommerce.EcommereceWebApp.entity.Cart;
import com.Ecommerce.EcommereceWebApp.entity.CartItem;
import com.Ecommerce.EcommereceWebApp.entity.Product;
import com.Ecommerce.EcommereceWebApp.entity.Users;
import com.Ecommerce.EcommereceWebApp.repo.CartItemRepo;
import com.Ecommerce.EcommereceWebApp.repo.CartRepo;
import com.Ecommerce.EcommereceWebApp.repo.ProductRepo;
import com.Ecommerce.EcommereceWebApp.repo.UserRepo;
import com.Ecommerce.EcommereceWebApp.serv.CartServ;

@Controller
@RequestMapping("/cart-api")
public class CartController {

	@Autowired
	private CartServ cartServ;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CartItemRepo cartitemRepo;
	@Autowired
	private CartRepo cartRepo;
	@Autowired
	private ProductRepo productRepo;
	
	@GetMapping("/cart")
	public String showCart(Principal pri,Model model)
	{
		Users user = userRepo.findByEmail(pri.getName()).orElseThrow(() -> new RuntimeException("user not found"));
     	Cart cart = cartServ.createCartForUser(user.getId());
     	model.addAttribute("cartItems",cart.getCart_items());
     	model.addAttribute("cartTotal", cart.getTotal_amount());
     	model.addAttribute("Customer_name", user.getName());
		return "Cart";
	}
	
	@PostMapping("/add-cart/{prod_id}")
	public String additeamstocart(
	        @PathVariable("prod_id") Long prod_id,
	        @RequestParam(defaultValue = "1") int q,
	        Principal pri
	) {
	    Users user = userRepo.findByEmail(pri.getName())
	            .orElseThrow(() -> new RuntimeException("user not found"));

	    cartServ.addtoCartItems(prod_id, user.getId(), q);

	    return "redirect:/cart-api/cart";
	}
	
	@PostMapping("/cart/updateAll")
	public String updateAllCartItems(@RequestParam Map<String,String> allParams, Principal principal) {
	    Users user = userRepo.findByEmail(principal.getName())
	            .orElseThrow(() -> new RuntimeException("User not found"));
	    Cart cart = cartServ.createCartForUser(user.getId());
	    for (CartItem item : cart.getCart_items()) {
	        String paramName = "quantity_" + item.getProduct().getProductId();
	        if (allParams.containsKey(paramName)) {
	            int qty = Integer.parseInt(allParams.get(paramName));
	            item.setQuantity(qty);
	            cartitemRepo.save(item);

	            Product product = item.getProduct();
	            int newStock = product.getStock() - qty; 

	            if (newStock < 0) {
	                throw new RuntimeException("Not enough stock for product: " + product.getProductName());
	            }
	            product.setStock(newStock);
	            productRepo.save(product); 
	        }
	    }

	    double total = 0;
	    for (CartItem item : cart.getCart_items()) {
	        total += item.getPrice() * item.getQuantity();
	    }
	    cart.setTotal_amount(total);
	    cartRepo.save(cart);

	    return "redirect:/cart-api/cart";
	}
	@GetMapping("/cart/remove/{id}")
	public String removeCartItem(@PathVariable("id") Long prodid,Principal pri)
	{
		Users user = userRepo.findByEmail(pri.getName()).orElseThrow(() -> new RuntimeException("user not found"));
		cartServ.deleteCartItem(prodid, user);
		return "redirect:/cart-api/cart";
	}




}
