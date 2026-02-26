package com.Ecommerce.EcommereceWebApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ecommerce.EcommereceWebApp.entity.ProductImg;

@Repository
public interface ProductImgRepo  extends JpaRepository<ProductImg,Long>{

	
}
