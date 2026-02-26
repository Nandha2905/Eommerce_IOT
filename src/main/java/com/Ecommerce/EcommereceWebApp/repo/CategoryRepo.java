package com.Ecommerce.EcommereceWebApp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ecommerce.EcommereceWebApp.entity.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {

	List<Category> findByActiveTrue();

	
}
