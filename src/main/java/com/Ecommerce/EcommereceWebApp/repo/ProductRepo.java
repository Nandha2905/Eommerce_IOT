package com.Ecommerce.EcommereceWebApp.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ecommerce.EcommereceWebApp.entity.Category;
import com.Ecommerce.EcommereceWebApp.entity.Product;
import com.Ecommerce.EcommereceWebApp.entity.Users;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long>{

	List<Product> findByCategory(Category category);


	List<Product> findByUserId(Long id);


	Optional<Product> findByProductIdAndUserEmail(Long productId, String email);


	List<Product> findByActiveTrueAndDeactivatedByAdminFalse();


	List<Product> findByCategoryAndActiveTrueAndDeactivatedByAdminFalse(Category category);
}
