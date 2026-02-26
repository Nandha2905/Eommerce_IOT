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

import com.Ecommerce.EcommereceWebApp.entity.Category;
import com.Ecommerce.EcommereceWebApp.entity.Product;
import com.Ecommerce.EcommereceWebApp.entity.Users;
import com.Ecommerce.EcommereceWebApp.repo.UserRepo;
import com.Ecommerce.EcommereceWebApp.serv.CategoryServ;
import com.Ecommerce.EcommereceWebApp.serv.ProductServ;
import com.Ecommerce.EcommereceWebApp.serv.UserServ;

@Controller
@RequestMapping("/user-api")
public class CustomerController {

	@Autowired
	private UserServ userServ;
	@Autowired
	private CategoryServ catSer;
	@Autowired
	private ProductServ prodSer;
	@Autowired
	private UserRepo userRepo;
	
	@GetMapping("/home")
	public String customerHome(Model model) {
		
		model.addAttribute("categories",catSer.getAllCategorys2());
		model.addAttribute("products", prodSer.getAllProduct());
		return "CustomerHome";
	}
	
	@GetMapping("/category/{id}")
	public String sepcificCategory(@PathVariable Long id,Model model)
	{
		List<Product> prod = prodSer.getProductsByCategory(id);
		Category cat = catSer.getCategoryById(id);
		model.addAttribute("products", prod);
		model.addAttribute("categoryName", cat.getCategoryName());
		return "CategoryProduct";
	}	
	@GetMapping("/dashboard")
	public String dash(Principal pri,Model model)
	{
		Users u = userRepo.findByEmail(pri.getName()).orElseThrow(() -> new RuntimeException("user not found"));
		model.addAttribute("User", u);
		return "user-dashboard";
	}
	
	@GetMapping("/edit-profile")
	public String editProf(Principal pri,Model model)
	{
		Users u = userRepo.findByEmail(pri.getName()).orElseThrow(() -> new RuntimeException("user not found"));
		model.addAttribute("user",u);
		return "edit-profile";
	}
	@PostMapping("/edit-profile")
	public String update(Users user)
	{
		Users u=userServ.getUserById(user.getId());
		if( u == null)
		{
			 throw new RuntimeException("User"+u.getId()+"not found");
		}
		
		if(user.getName()!= null)
		{
			u.setName(user.getName());
		}
		if(user.getEmail() != null)
		{
			u.setEmail(user.getEmail());
		}
		if(user.getPhone_Number() != null)
		{
			u.setPhone_Number(user.getPhone_Number());
		}
		
		userRepo.save(u);
		return "redirect:/user-api/dashboard";
	}
}
