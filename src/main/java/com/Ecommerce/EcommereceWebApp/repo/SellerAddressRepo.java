package com.Ecommerce.EcommereceWebApp.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.Ecommerce.EcommereceWebApp.entity.SellerAddress;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerAddressRepo extends JpaRepository<SellerAddress, Long> {

    // Find seller address by seller's id
    SellerAddress findBySeller_Id(Long sellerId);
}