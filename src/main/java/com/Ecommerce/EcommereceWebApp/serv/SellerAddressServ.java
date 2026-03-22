package com.Ecommerce.EcommereceWebApp.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecommerce.EcommereceWebApp.entity.SellerAddress;
import com.Ecommerce.EcommereceWebApp.repo.SellerAddressRepo;

@Service
public class SellerAddressServ {

    @Autowired
    private SellerAddressRepo sellerAddressRepo;

    public SellerAddress save(SellerAddress address) {
        return sellerAddressRepo.save(address);
    }

    public SellerAddress findBySellerId(Long sellerId) {
        return sellerAddressRepo.findBySeller_Id(sellerId);
    }
}