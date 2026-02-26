package com.Ecommerce.EcommereceWebApp.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Ecommerce.EcommereceWebApp.entity.Users;
import com.Ecommerce.EcommereceWebApp.entity.UsersAddress;
import com.Ecommerce.EcommereceWebApp.repo.UserAddressRepo;
import com.Ecommerce.EcommereceWebApp.repo.UserRepo;
import com.Ecommerce.EcommereceWebApp.serv.UserAddressServ;
import com.Ecommerce.EcommereceWebApp.serv.UserServ;

@Controller 
@RequestMapping("/user-address-api")
public class UserAddressController {

	
	@Autowired
	private UserAddressServ useraddaServ;
	@Autowired 
	private UserServ userSer;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private UserAddressRepo userAddRepo;
	
	@GetMapping("/manage-user-address")
	public String manageAdd(Principal pri , Model model)
	{
		Users u = userRepo.findByEmail(pri.getName()).orElseThrow(() -> new RuntimeException("user  not found "));
		UsersAddress ua = useraddaServ.getAddressByUser(u);
		model.addAttribute("address", ua);
		model.addAttribute("user", u);
		return "Manage-userAddress";
    }
	@PostMapping("/manage-user-address")
	public String manageAdd(UsersAddress userAdd)
	{
		UsersAddress ua = useraddaServ.getUserById(userAdd.getAddressId());
		if(ua  == null) throw new RuntimeException("address not found");
		
		if(userAdd.getCity() != null)
		{
			ua.setCity(userAdd.getCity());
		}
		if(userAdd.getPincode() != null)
		{
			ua.setPincode(userAdd.getPincode());
		}
		if(userAdd.getState() != null)
		{
			ua.setState(userAdd.getState());
		}
		if(userAdd.getStreet() != null)
		{
			ua.setStreet(userAdd.getStreet());
		}
		userAddRepo.save(ua);
		return "redirect:/user-address-api/manage-user-address";
	}
	

}