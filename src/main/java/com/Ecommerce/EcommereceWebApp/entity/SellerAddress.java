package com.Ecommerce.EcommereceWebApp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "seller_address")
public class SellerAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    // Street / Building name
    @Column(nullable = false)
    private String street;

    // City
    @Column(nullable = false)
    private String city;

    // District (extra field for sellers - useful in India)
    @Column
    private String district;

    // State
    @Column(nullable = false)
    private String state;

    // Pincode
    @Column(nullable = false, length = 6)
    private String pincode;

    // One seller has one business address (One-to-One)
    @OneToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private Users seller;

    // ===================== Constructors =====================

    public SellerAddress() {}

    public SellerAddress(String street, String city, String district,
                         String state, String pincode, Users seller) {
        this.street   = street;
        this.city     = city;
        this.district = district;
        this.state    = state;
        this.pincode  = pincode;
        this.seller   = seller;
    }

    // ===================== Getters & Setters =====================

    public Long getAddressId() { return addressId; }
    public void setAddressId(Long addressId) { this.addressId = addressId; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }

    public Users getSeller() { return seller; }
    public void setSeller(Users seller) { this.seller = seller; }

    @Override
    public String toString() {
        return street + ", " + city + ", " + district + ", " + state + " - " + pincode;
    }
}