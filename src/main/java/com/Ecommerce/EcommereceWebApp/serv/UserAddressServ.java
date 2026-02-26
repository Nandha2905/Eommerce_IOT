package com.Ecommerce.EcommereceWebApp.serv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecommerce.EcommereceWebApp.entity.Users;
import com.Ecommerce.EcommereceWebApp.entity.UsersAddress;
import com.Ecommerce.EcommereceWebApp.repo.UserAddressRepo;

@Service
public class UserAddressServ {

	@Autowired
	private UserAddressRepo userAddRepo;
	public List<UsersAddress> getAllAddress()
	{
		return userAddRepo.findAll();
	}
	public  UsersAddress getUserById(Long id)
	{
		return userAddRepo.findById(id).orElseThrow(() -> new RuntimeException("address "+id+" not found"));
	}
	public void addAddress(UsersAddress ad)
	{
		userAddRepo.save(ad);
	}
	public UsersAddress getAddressByUser(Users u)
	{
		return userAddRepo.findByUser(u);
	}
}
