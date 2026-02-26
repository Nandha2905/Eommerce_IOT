package com.Ecommerce.EcommereceWebApp.serv;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecommerce.EcommereceWebApp.entity.Users;
import com.Ecommerce.EcommereceWebApp.repo.UserRepo;

@Service
public class UserServ {

	@Autowired
	private UserRepo userRepo;
	
	public List<Users> getAllUsers(){
		return userRepo.findAll();
	}
	public Users getUserById(Long id)
	{
		return userRepo.findById(id).orElseThrow(() -> new RuntimeException("user id"+id+"NotFound"));

	}
	public Users getUserByEmailandPassword(Users user)
	{
		return userRepo.findByEmailAndPassword(user.getEmail(),user.getPassword());
	}
	public Users createUser(Users user)
	{
		return userRepo.save(user);
	}
	public void updateUsers(Users user)
	{
		Users u= userRepo.findById(user.getId()).orElseThrow(() -> new RuntimeException("user id"+user.getId()+"NotFound"));
		if(u == null)
		{
			throw new RuntimeException("User Not Found ");
		}
		 if(u.getName() != null )
		 {
			 u.setName(user.getName());
		 }
		 if(u.getEmail() != null)
		 {
			 u.setEmail(user.getEmail());
		 }
		 if(u.getPhone_Number() != null)
		 {
			 u.setPhone_Number(user.getPhone_Number());
		 }
		 if(u.getPassword() != null)
		 {
			 u.setPassword(user.getPassword());
		 }
		 
		 userRepo.save(u);
	}
	public void deleteByID(Long id)
	{
		userRepo.findById(id).orElseThrow(() -> new RuntimeException("user id"+id+"NotFound"));
		userRepo.deleteById(id);
	}
	public List<Users> getAllCustomer() {
		
		return userRepo.findByRole("CUSTOMER");
	}
	public List<Users> getAllSeller() {
		// TODO Auto-generated method stub
		return userRepo.findByRole("SELLER");
	}
	  public void toggleActiveStatus(Long id) {
	        Users customer = userRepo.findById(id).orElseThrow(()-> new  RuntimeException("User Not Found"));
	        customer.setActive(!customer.isActive());
	        userRepo.save(customer);
	    }
	
}
