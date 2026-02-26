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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Ecommerce.EcommereceWebApp.entity.Category;
import com.Ecommerce.EcommereceWebApp.entity.Order;
import com.Ecommerce.EcommereceWebApp.entity.OrderItem;
import com.Ecommerce.EcommereceWebApp.entity.Product;
import com.Ecommerce.EcommereceWebApp.entity.Users;
import com.Ecommerce.EcommereceWebApp.repo.OrderItemRepo;
import com.Ecommerce.EcommereceWebApp.repo.OrderRepo;
import com.Ecommerce.EcommereceWebApp.repo.UserRepo;
import com.Ecommerce.EcommereceWebApp.serv.CategoryServ;
import com.Ecommerce.EcommereceWebApp.serv.OrderServ;
import com.Ecommerce.EcommereceWebApp.serv.ProductServ;
import com.Ecommerce.EcommereceWebApp.serv.UserServ;

@Controller
@RequestMapping("/admin-api")
public class AdminController {

	@Autowired
	private UserServ userServ;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private OrderRepo ordRepo;
	@Autowired
	private ProductServ prdServ;
	@Autowired
	private OrderServ orderServ;
	@Autowired
	private CategoryServ catServ;
	@Autowired
	private OrderServ ordServ;
	@Autowired
	private OrderItemRepo ordItemRepo;
	
	@GetMapping("/dashboard")
	public String getAdminHome(Model model) {

	    model.addAttribute("totalCustomers", userServ.getAllCustomer().size());
	    model.addAttribute("totalSellers", userServ.getAllSeller().size());
	    model.addAttribute("totalProducts", prdServ.getAllProduct2().size());
	    model.addAttribute("totalCategories",catServ.getAllCategorys().size());
	    model.addAttribute("recentOrders", orderServ.getRecentOrders());
	    model.addAttribute("totalOrders",ordServ.getAllOrder().size());
	   

	    return "admin-dashboard";
	}

	@GetMapping("/manage-customer")
	public String manageCustomerPage(Model model)
	{
		model.addAttribute("customers", userServ.getAllCustomer());
		return "manage-customer";
	}
	@GetMapping("/manage-seller")
	public String manageSeller(Model model)
	{
		model.addAttribute("sellers",userServ.getAllSeller());
		return "manage-seller";
	}
//	@GetMapping("/edit-customer-form/{id}")
//	public String editcustomerpage(@PathVariable Long id,Model model)
//	{
//		Users user = userServ.getUserById(id);
//		model.addAttribute("cutomer", user);
//		return "customer-edit-page";
//	}
//	@GetMapping("/edit-seller-form/{id}")
//	public String editSellerpage(@PathVariable Long id,Model model)
//	{
//		Users user = userServ.getUserById(id);
//		model.addAttribute("seller", user);
//		return "seller-edit-page";
//	}
//	@PostMapping("/update-customer")
//	public String updateCustomer(Users user, RedirectAttributes redirectAttributes) {
//	    try {
//	        userServ.updateUsers(user);
//	        redirectAttributes.addFlashAttribute("msg", 
//	            "Customer updated successfully!");
//	    } catch (Exception e) {
//	        redirectAttributes.addFlashAttribute("error", 
//	            "Failed to update customer. Please try again.");
//	        return "redirect:/admin-api/edit-customer-form/" + user.getId();
//	    }
//	    return "redirect:/admin-api/manage-customer";
//	}
//	@PostMapping("/update-seller")
//	public String updateSeller(Users user, RedirectAttributes redirectAttributes) {
//	    try {
//	        userServ.updateUsers(user);
//	        redirectAttributes.addFlashAttribute("msg", 
//	            "Customer updated successfully!");
//	    } catch (Exception e) {
//	        redirectAttributes.addFlashAttribute("error", 
//	            "Failed to update customer. Please try again.");
//	        return "redirect:/admin-api/edit-customer-form/" + user.getId();
//	    }
//	    return "redirect:/admin-api/manage-seller";
//	}
	@GetMapping({"/toggle-customer/{id}",})
	public String toggleCustomer(@PathVariable Long id, RedirectAttributes redirectAttributes)
	{
		
		userServ.toggleActiveStatus(id);
		Users user = userServ.getUserById(id);
		if(user.isActive()) {
		redirectAttributes.addFlashAttribute("msg", 
	            "Customer Enabled successfully!");
		}
		else {
			redirectAttributes.addFlashAttribute("error", 
		            "Customer Disabled successfully!");
		}
	    return "redirect:/admin-api/manage-customer";
	}
	@GetMapping("/toggle-seller/{id}")
	public String toggleSeller(@PathVariable Long id, RedirectAttributes redirectAttributes)
	{
		
		userServ.toggleActiveStatus(id);
		Users user = userServ.getUserById(id);
		if(user.isActive()) {
		redirectAttributes.addFlashAttribute("msg", 
	            "Seller Enabled successfully!");
		}
		else {
			redirectAttributes.addFlashAttribute("error", 
		            "Seller Disabled successfully!");
		}
	    return "redirect:/admin-api/manage-seller";
	}

//	@GetMapping("/delete-customer/{id}")
//	public String delletcustomer(@PathVariable Long id,RedirectAttributes redirectAttributes)
//	{
//		try {
//	        userServ.deleteByID(id);
//	        redirectAttributes.addFlashAttribute("msg",
//	                "User deleted successfully!");
//	    } catch (Exception e) {
//	        redirectAttributes.addFlashAttribute("error",
//	                "Failed to delete user.");
//	    }
//		return "redirect:/admin-api/manage-customer";
//	}
//	@GetMapping("/delete-seller/{id}")
//	public String delletSeller(@PathVariable Long id,RedirectAttributes redirectAttributes)
//	{
//		try {
//	        userServ.deleteByID(id);
//	        redirectAttributes.addFlashAttribute("msg",
//	                "User deleted successfully!");
//	    } catch (Exception e) {
//	        redirectAttributes.addFlashAttribute("error",
//	                "Failed to delete user.");
//	    }
//		return "redirect:/admin-api/manage-seller";
//	}

	
	 @GetMapping("/manage-product")
	 public String manageProduct(Model model)
	 {
		 model.addAttribute("Product",prdServ. getAllProduct2());
		 return "manage-product";
	 }
	 @GetMapping("/delete-product/{id}")
	 public String deleteProduct(@PathVariable Long id,RedirectAttributes redirectAttributes)
	 {
		 try {
			 prdServ.deleteProductById(id);
		        redirectAttributes.addFlashAttribute("msg",
		                "User deleted successfully!");
		    } catch (Exception e) {
		        redirectAttributes.addFlashAttribute("error",
		                "Failed to delete user.");
		    }
			return "redirect:/admin-api/manage-product"; 
	 }
	 @GetMapping("/toggle-product/{id}")
	 public String toggleProduct(@PathVariable Long id, RedirectAttributes redirectAttributes, Principal pri) {
	     Users user = userRepo.findByEmail(pri.getName())
	                          .orElseThrow(() -> new RuntimeException("User not found"));

	     Product product = prdServ.getProductById(id);
	     boolean newStatus = !product.isActive();
	     prdServ.toggleProduct(id, user, newStatus);
	     
	     if (newStatus) {
	         redirectAttributes.addFlashAttribute("msg", "Product Enabled successfully!");
	     } else {
	         redirectAttributes.addFlashAttribute("error", "Product Disabled successfully!");
	     }

	     return "redirect:/admin-api/manage-product";
	 }
	 @GetMapping("/manage-orders")
	 public String manageorder(Model model)
	 {
		 model.addAttribute("orders",orderServ.getAllOrder());
		 model.addAttribute("totalOrders",orderServ.getAllOrder().size());
		 return "manage-order";
	 }
	 @GetMapping("/view-order-items/{orderId}")
	 public String viewOrderItems(@PathVariable Long orderId, Model model) {

	     Order order = ordRepo.findById(orderId)
	             .orElseThrow(() -> new RuntimeException("Order not found"));

	     List<OrderItem> items = ordItemRepo.findByOrder_OrderId(orderId);
	     double grandTotal = items.stream()
	             .mapToDouble(i -> i.getQuantity() * i.getPrice())
	             .sum();
	     model.addAttribute("order", order);
	     model.addAttribute("items", items);
	     model.addAttribute("grandTotal", grandTotal);
	     return "admin-view-order-items";
	 }
	 @GetMapping("/manage-categories")
	 public String manageCat(Model model)
	 {
		 model.addAttribute("categories",catServ.getAllCategorys());
		 return "manage-category";
	 }
	 @GetMapping("/toggle-category/{id}")
	 public String togglecategory(@PathVariable Long id,RedirectAttributes redirectAttributes)
	 {
		
		 catServ.toogle(id);
		 Category  ct = catServ.getCategoryById(id);
		 if(ct.isActive()) {
				redirectAttributes.addFlashAttribute("msg", 
			            "Category Enabled successfully!");
				}
				else {
					redirectAttributes.addFlashAttribute("error", 
				            "Category Disabled successfully!");
				}
			return"redirect:/admin-api/manage-categories";
	 }
	 @PostMapping("/add-category")
	 public String addCat(Category cat,RedirectAttributes redirectAttributes)
	 {
		 catServ.createCategory(cat);
		 redirectAttributes.addFlashAttribute("msg","Category Was Successfully Added!");
		 return "redirect:/admin-api/manage-categories";
	 }
	 @PostMapping("/update-category")
	 public String updateCat(Category ct,RedirectAttributes redirectAttributes)
	 {
		  try {
			  catServ.updateCat(ct);
		        redirectAttributes.addFlashAttribute("msg", 
		            "Customer updated successfully!");
		    } catch (Exception e) {
		        redirectAttributes.addFlashAttribute("error", 
		            "Failed to update customer. Please try again.");
		    }
		    return "redirect:/admin-api/manage-categories";
	 }
}
