package com.Ecommerce.EcommereceWebApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Ecommerce.EcommereceWebApp.entity.Category;
import com.Ecommerce.EcommereceWebApp.entity.Product;
import com.Ecommerce.EcommereceWebApp.serv.CategoryServ;
import com.Ecommerce.EcommereceWebApp.serv.ProductServ;

@Controller
public class HomeController {

	@Autowired
	private CategoryServ catSer;
	@Autowired
	private ProductServ prodSer;
	

	@GetMapping("/")
	public  String Home() {
		return "HomePage";
	}
	
}
