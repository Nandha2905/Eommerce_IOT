package com.Ecommerce.EcommereceWebApp.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Ecommerce.EcommereceWebApp.entity.Cart;
import com.Ecommerce.EcommereceWebApp.entity.Order;
import com.Ecommerce.EcommereceWebApp.entity.OrderAddress;
import com.Ecommerce.EcommereceWebApp.entity.OrderItem;
import com.Ecommerce.EcommereceWebApp.entity.Product;
import com.Ecommerce.EcommereceWebApp.entity.Users;
import com.Ecommerce.EcommereceWebApp.repo.CartRepo;
import com.Ecommerce.EcommereceWebApp.repo.OrderItemRepo;
import com.Ecommerce.EcommereceWebApp.repo.ProductRepo;
import com.Ecommerce.EcommereceWebApp.repo.UserRepo;
import com.Ecommerce.EcommereceWebApp.serv.OrderServ;
import com.Ecommerce.EcommereceWebApp.serv.UserServ;

@Controller
@RequestMapping("/order-api")
public class OrderController {

	@Autowired
	private OrderServ  orderServ;
	@Autowired
	private UserServ userServ;
	@Autowired
	private OrderItemRepo orderitemrepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired 
	private ProductRepo proRepo;
	@Autowired
	private CartRepo cartRepo;
	@GetMapping("/buy-now/{productId}")
	public String buyNow(@PathVariable("productId") Long productId,
            @RequestParam(defaultValue = "1") int quantity,Principal pri,Model model)
	{
	  Users user = userRepo.findByEmail(pri.getName()).orElseThrow(() -> new RuntimeException("user not found"));
	  Product product = proRepo.findById(productId).orElseThrow(() -> new RuntimeException("product not found"));
	  model.addAttribute("orderType", "BUY_NOW");
      model.addAttribute("product", product);
      model.addAttribute("quantity", quantity);
      model.addAttribute("user", user);
      model.addAttribute("defaultAddress", user.getUsersAddress());
		return "Order";
	}
	
	@PostMapping("/place")
	public String placeOrder(
	        @RequestParam String orderType,
	        @RequestParam(required = false) Long productId,
	        @RequestParam(required = false) Integer quantity,
	        @RequestParam String addressType,

	        @RequestParam(required = false) String street,
	        @RequestParam(required = false) String city,
	        @RequestParam(required = false) String state,
	        @RequestParam(required = false) String pincode,

	        @RequestParam Long userId) {

	    OrderAddress customAddress = new OrderAddress();

	    if ("CUSTOM".equalsIgnoreCase(addressType)) {
	        customAddress.setStreet(street);
	        customAddress.setCity(city);
	        customAddress.setState(state);
	        customAddress.setPincode(pincode);
	    }

	    if ("BUY_NOW".equalsIgnoreCase(orderType)) {
	        orderServ.placeOrderDirect(
	                productId, quantity, customAddress, addressType, userId
	        );
	    } else {
	        Users user = userRepo.findById(userId).orElseThrow();
	        Cart cart = cartRepo.findByUser(user);
	        orderServ.placeOrderByCart(
	                cart, customAddress, addressType, userId
	        );
	    }

	    return "redirect:/order-api/success";
	}
	@GetMapping("/success")
	public String successs(Principal pri,Model model)
	{
		 Users u = userRepo.findByEmail(pri.getName()).orElseThrow(() -> new RuntimeException("user not found"));
		 model.addAttribute("Users", u);
		return "Success";
	}
	
	@GetMapping("/orders/{userid}")
	public String myOrders(@PathVariable Long userid,Model model)
	{
		List<Order> or = orderServ.getorderByUserid(userid);
		Users u=userServ.getUserById(userid);
		model.addAttribute("Orders",or);
		model.addAttribute("User", u);
		return "MyOrders";
	}
	
	@GetMapping("/order-items/{orderId}")
	public String orderItems(@PathVariable Long orderId,Model model)
	{
		List<OrderItem> OI = orderitemrepo.findByOrder_OrderId(orderId);
		model.addAttribute("items",OI);
		model.addAttribute("order",orderId);
		return "order-item";
	}

	@GetMapping("/checkout")
	public String checkoutCart(Principal pri, Model model) {
	    Users user = userRepo.findByEmail(pri.getName())
	                 .orElseThrow(() -> new RuntimeException("User not found"));
	
	    Cart cart = cartRepo.findByUser(user);
	
	    model.addAttribute("orderType", "CART");
	    model.addAttribute("cartItems", cart.getCart_items());
	    model.addAttribute("cartTotal", cart.getTotal_amount());
	    model.addAttribute("user", user);
	    model.addAttribute("defaultAddress", user.getUsersAddress());
	
	    return "Order"; 
	   }
	
}
