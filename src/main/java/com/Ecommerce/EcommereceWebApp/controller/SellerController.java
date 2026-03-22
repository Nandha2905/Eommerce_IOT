package com.Ecommerce.EcommereceWebApp.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Ecommerce.EcommereceWebApp.entity.Category;
import com.Ecommerce.EcommereceWebApp.entity.Order;
import com.Ecommerce.EcommereceWebApp.entity.OrderItem;
import com.Ecommerce.EcommereceWebApp.entity.Product;
import com.Ecommerce.EcommereceWebApp.entity.Users;
import com.Ecommerce.EcommereceWebApp.repo.UserRepo;
import com.Ecommerce.EcommereceWebApp.serv.CategoryServ;
import com.Ecommerce.EcommereceWebApp.serv.OrderItemServ;
import com.Ecommerce.EcommereceWebApp.serv.OrderServ;
import com.Ecommerce.EcommereceWebApp.serv.ProductServ;
import com.Ecommerce.EcommereceWebApp.serv.UserServ;

@Controller
@RequestMapping("/seller-api")
public class SellerController {

	@Autowired
	private OrderServ ordserv;
	@Autowired
	private UserServ userServ;
	@Autowired 
	private UserRepo userRepo;
	@Autowired
	private ProductServ prodSrv;
	@Autowired
	private OrderItemServ ordItemServ;
	@Autowired 
	private CategoryServ catServ;
	
	@GetMapping("/profile")
	public String sellerProfile(Principal pri,Model model)
	{
			Users u = userRepo.findByEmail(pri.getName()).orElseThrow(() -> new RuntimeException("user not found"));
			model.addAttribute("User", u);
			return "seller-profile";
	}
	
	@GetMapping("/dashboard")
	public String getSellerHome(Principal pri,Model model)
	{
		Users  seller = userRepo.findByEmail(pri.getName()).orElseThrow(() -> new RuntimeException("user not found"));
		List<Product> p = prodSrv.getProductsByUser(seller.getId());
		List<OrderItem> ordItems = ordItemServ.getBysellerId(seller.getId());

	    // Get distinct orders
	    List<Order> distinctOrders = ordItems.stream()
	            .map(OrderItem::getOrder)  // get the Order from each OrderItem
	            .distinct()               // remove duplicates
	            .collect(Collectors.toList());
		model.addAttribute("productList", p);
		model.addAttribute("totalProduct",p.size());
		model.addAttribute("seller",seller);
		model.addAttribute("ordersList", distinctOrders);
		model.addAttribute("ordersItemList",ordItems);
		model.addAttribute("totalOrders",ordItems.size());
		return "seller-dashboard";
	}
	
	@GetMapping("/view-orders/{orderid}")
	public String getOrders(@PathVariable Long orderid,Model model)
	{
		Order order = ordserv.getOrderById(orderid);
		List<OrderItem> ordlist = ordItemServ.getByOrderId(orderid);
		model.addAttribute("orderItems",ordlist);
		model.addAttribute("order", order);
		model.addAttribute("totalAmount", order.getTotal_amount());
		return "seller-order-view-Page";
	}
	
	@GetMapping("/manage-seller-products")
	public String getprod(Model model,Principal pri) {
		
		Users  seller = userRepo.findByEmail(pri.getName()).orElseThrow(() -> new RuntimeException("user not found"));
		List<Product> p = prodSrv.getProductsByUser(seller.getId());
		model.addAttribute("productList", p);
		return "Manage-seller-product";
	}
	@GetMapping("/add-products-form")
	public String addprod(Model model)
	{
		model.addAttribute("categoryList",catServ.getAllCategorys2() );
		return "seller-add-product";
	}
	@PostMapping("/add-products")
	public String addprod(@RequestParam("categoryId") Long categoryId, Product pro,Principal pri,RedirectAttributes ra)
	{
		Users  seller = userRepo.findByEmail(pri.getName()).orElseThrow(() -> new RuntimeException("user not found"));
        Category c = catServ.getCategoryById(categoryId);
		pro.setUser(seller);
		pro.setCategory(c);
		prodSrv.saveProduct(pro);
		ra.addFlashAttribute("msg","prouduct added successfully!");
		return "redirect:/seller-api/manage-seller-products";
	}
	
	@GetMapping("/update-products-form/{id}")
	public String editpage(@PathVariable("id") Long id,
            Principal principal,
            Model model) {

            Product pro = prodSrv.getProductById(id);
            model.addAttribute("product", pro);
            model.addAttribute("categoryList",catServ.getAllCategorys2() );
           return "seller-edit-form";
}
	@PostMapping("/update-products/{id}")
	public String updatePro(@PathVariable Long id,
	                        Product pro,
	                        @RequestParam("categoryId") Long categoryId,
	                        Principal principal) {

	    prodSrv.updateProduct(id, pro, principal.getName(), categoryId);

	    return "redirect:/seller-api/manage-seller-products";
	}
	@GetMapping("/toggle-product/{id}")
	public String toggleProd(@PathVariable Long id, Principal pri,RedirectAttributes ra) {
		try {
	    Product product = prodSrv.getProductById(id);
	    Users seller = userRepo.findByEmail(pri.getName()).orElseThrow(() -> new RuntimeException("user not found"));
	    prodSrv.toggleProduct(id, seller, !product.isActive());
	    ra.addFlashAttribute("msg", 
	            "Product " + (product.isActive() ? "activated" : "deactivated") + " successfully!");
		}
		catch(Exception e)
		{
			 ra.addFlashAttribute("error",e.getMessage());
		}
	    return "redirect:/seller-api/manage-seller-products";
	}
	
}
