package com.Ecommerce.EcommereceWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Ecommerce.EcommereceWebApp.entity.Users;
import com.Ecommerce.EcommereceWebApp.entity.UsersAddress;
import com.Ecommerce.EcommereceWebApp.serv.UserAddressServ;
import com.Ecommerce.EcommereceWebApp.serv.UserServ;

@Controller
@RequestMapping("/login-api")
public class LoginController {

	@Autowired
	private PasswordEncoder passEncoder;
	@Autowired
	private UserServ userServ;
	@Autowired
	private UserAddressServ userAddServ;
	
	@GetMapping("/login-form")
	public String loginForm()
	{
		return "LoginPage";
	}
	
	@GetMapping("/register-form")
	public String registerForm()
	{
		return "RegisterPage";
	}
	
	@PostMapping("/register")
	public String registration(
	        Users customer,
	        UsersAddress userAdd,
	        RedirectAttributes redirectAttributes) {

	    customer.setUsersAddress(userAdd);
	    userAdd.setUser(customer);
	    customer.setPassword(passEncoder.encode(customer.getPassword()));
	    userServ.createUser(customer); 
	    redirectAttributes.addFlashAttribute("msg", "Registered Successfully !!");
	    return "redirect:/login-api/login-form";
	}

}
