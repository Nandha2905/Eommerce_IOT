package com.Ecommerce.EcommereceWebApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ecommerce.EcommereceWebApp.entity.Users;
import com.Ecommerce.EcommereceWebApp.entity.UsersAddress;

@Repository
public interface UserAddressRepo extends JpaRepository<UsersAddress,Long>{

	UsersAddress findByUser(Users u);

	
}
