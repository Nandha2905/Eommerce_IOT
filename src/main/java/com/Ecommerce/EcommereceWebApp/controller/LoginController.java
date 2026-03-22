package com.Ecommerce.EcommereceWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Ecommerce.EcommereceWebApp.entity.SellerAddress;
import com.Ecommerce.EcommereceWebApp.entity.Users;
import com.Ecommerce.EcommereceWebApp.entity.UsersAddress;
import com.Ecommerce.EcommereceWebApp.serv.SellerAddressServ;
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

	@Autowired
	private SellerAddressServ sellerAddressServ;

	// ─── Login Page ────────────────────────────────────────────────
	@GetMapping("/login-form")
	public String loginForm() {
		return "LoginPage";
	}

	// ─── Register Page ─────────────────────────────────────────────
	@GetMapping("/register-form")
	public String registerForm() {
		return "RegisterPage";
	}

	// ─── Customer Registration (your existing logic - unchanged) ───
	@PostMapping("/register")
	public String registration(
			Users customer,
			UsersAddress userAdd,
			RedirectAttributes redirectAttributes) {

		customer.setUsersAddress(userAdd);
		userAdd.setUser(customer);
		customer.setPassword(passEncoder.encode(customer.getPassword()));
		customer.setRole("CUSTOMER");
		customer.setActive(true); // customers active immediately
		userServ.createUser(customer);

		redirectAttributes.addFlashAttribute("msg", "Registered Successfully !!");
		return "redirect:/login-api/login-form";
	}

	// ─── Seller Registration (new) ─────────────────────────────────
	@PostMapping("/register-seller")
	public String registerSeller(
			// Personal Info
			@RequestParam String name,
			@RequestParam String email,
			@RequestParam String Phone_Number,
			@RequestParam String password,
			@RequestParam String confirmPassword,

			// Business Info
			@RequestParam String businessName,
			@RequestParam String businessType,
			@RequestParam(required = false) String gstNumber,
			@RequestParam(required = false) String category,

			// Seller Address
			@RequestParam String sellerStreet,
			@RequestParam String sellerCity,
			@RequestParam String sellerDistrict,
			@RequestParam String sellerState,
			@RequestParam String sellerPincode,

			RedirectAttributes ra) {

		// 1. Password match check
		if (!password.equals(confirmPassword)) {
			ra.addFlashAttribute("error", "Passwords do not match!");
			return "redirect:/login-api/register-form";
		}

		if (userServ.existsByEmail(email)) {
			ra.addFlashAttribute("error", "Email already registered!");
			return "redirect:/login-api/register-form";
		}

		// 3. Build seller Users object
		Users seller = new Users();
		seller.setName(name);
		seller.setEmail(email);
		seller.setPhone_Number(Phone_Number);
		seller.setPassword(passEncoder.encode(password));
		seller.setRole("SELLER");
		seller.setActive(false); // ← false: needs admin approval

		Users savedSeller = userServ.createUser(seller);

		SellerAddress address = new SellerAddress();
		address.setStreet(sellerStreet);
		address.setCity(sellerCity);
		address.setDistrict(sellerDistrict);
		address.setState(sellerState);
		address.setPincode(sellerPincode);
		address.setSeller(savedSeller);

		sellerAddressServ.save(address);

		ra.addFlashAttribute("msg",
				"Seller account submitted! Please wait for admin approval before logging in.");
		return "redirect:/login-api/login-form";
	}
}