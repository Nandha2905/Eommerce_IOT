package com.Ecommerce.EcommereceWebApp.serv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecommerce.EcommereceWebApp.entity.Category;
import com.Ecommerce.EcommereceWebApp.entity.Product;
import com.Ecommerce.EcommereceWebApp.entity.Users;
import com.Ecommerce.EcommereceWebApp.repo.CategoryRepo;
import com.Ecommerce.EcommereceWebApp.repo.ProductRepo;

@Service
public class ProductServ {

	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private CategoryRepo catRepo;
	
	public List<Product> getAllProduct()
	{
	    return productRepo.findByActiveTrueAndDeactivatedByAdminFalse();
	}
	public List<Product> getAllProduct2()
	{
		return productRepo.findAll();
	}
	public Product getProductById(Long id)
	{
		return productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product "+ id +"not found"));
	}
	public void saveProduct(Product pro)
	{
		productRepo.save(pro);
	}
	public void updateProduct(Long id, Product pro, String email, Long catId) {

	    if (id == null) {
	        throw new RuntimeException("Product ID cannot be null");
	    }

	    Product existing = productRepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Product not found"));

	    Category ct = catRepo.findById(catId)
	            .orElseThrow(() -> new RuntimeException("Category not found"));

	    existing.setProductName(pro.getProductName());
	    existing.setDescription(pro.getDescription());
	    existing.setPrice(pro.getPrice());
	    existing.setStock(pro.getStock());
	    existing.setCategory(ct);

	    productRepo.save(existing);
	}

	//security purpose	
//	public Product getProductForSeller(Long productId, String email) {
//
//	    return productRepo
//	            .findByProductIdAndUserEmail(productId, email).orElseThrow(() -> new RuntimeException("Product not found or access denied"));
//	}
	public void deleteProductById(Long id)
	{
		productRepo.findById(id).orElseThrow(() -> new RuntimeException("product"+id+"not found"));
		productRepo.deleteById(id);
	}
//	public void toggle(Long id) {
//
//	    Product product = productRepo.findById(id)
//	            .orElseThrow(() -> new RuntimeException("Product " + id + " not found"));
//	    product.setActive(!product.isActive()); 
//	    productRepo.save(product);
//	}
	
	public void toggleProduct(Long productId, Users user, boolean activate) {
	    Product product = productRepo.findById(productId)
	                         .orElseThrow(() -> new RuntimeException("Product not found"));

	    if (user.getRole().equals("SELLER")) {
	        if (!product.getUser().getId().equals(user.getId())) {
	            throw new RuntimeException("You cannot modify other sellers' products");
	        }
	        if (product.getDeactivatedByAdmin() && activate) {
	            throw new RuntimeException("Admin has deactivated this product. Cannot activate it back.");
	        }
	        product.setActive(activate);
	    } 
	    else if (user.getRole().equals("ADMIN")) {
	        product.setActive(activate);
	        if (!activate) {
	            product.setDeactivatedByAdmin(true);
	        } else {
	            product.setDeactivatedByAdmin(false);
	        }
	    } 
	    else {
	        throw new RuntimeException("Customers cannot modify products");
	    }

	    productRepo.save(product);
	}

//	public List<Product> getProductsByCategory(Long category_id)
//	{
//		Category ct = catRepo.findById(category_id).orElseThrow(() -> new RuntimeException("not founs"));
//		return productRepo.findByCategory(ct);
//	}
	public List<Product> getProductsByCategory(Long categoryId) {
	    Category category = catRepo.findById(categoryId)
	                               .orElseThrow(() -> new RuntimeException("Category not found"));
	    
	    return productRepo.findByCategoryAndActiveTrueAndDeactivatedByAdminFalse(category);
	}
	public List<Product> getProductsByUser(Long id) {
		return  productRepo.findByUserId(id);
	}
}
