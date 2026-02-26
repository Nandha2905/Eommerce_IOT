package com.Ecommerce.EcommereceWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Ecommerce.EcommereceWebApp.serv.ProductServ;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductServ productServ;
	


}
