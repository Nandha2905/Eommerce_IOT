package com.Ecommerce.EcommereceWebApp.serv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecommerce.EcommereceWebApp.entity.Category;
import com.Ecommerce.EcommereceWebApp.repo.CategoryRepo;

@Service
public class CategoryServ {

	@Autowired
	private CategoryRepo catRepo;
	
	public List<Category> getAllCategorys2()
	{
		return  catRepo.findByActiveTrue();
	}
	public List<Category> getAllCategorys()
	{
		return  catRepo.findAll();
	}
	public Category getCategoryById(Long id)
	{
		return catRepo.findById(id).orElseThrow(() -> new RuntimeException("not found"));
	}
	
	public void createCategory(Category cat)
	{
		catRepo.save(cat);
	}
	
	public void toogle(Long id)
	{
		Category ct = catRepo.findById(id).orElseThrow(() -> new RuntimeException("not found"));
		ct.setActive(!ct.isActive());
		catRepo.save(ct);
	}

	 
	    public void updateCat(Category ct) {

	        Category existing = catRepo.findById(ct.getCategoryId())
	                .orElseThrow(() -> new RuntimeException("Category not found"));

	        existing.setCategoryName(ct.getCategoryName());
	        existing.setCategoryDesc(ct.getCategoryDesc());
	        existing.setActive(ct.isActive());

	        catRepo.save(existing);
	    }
}
