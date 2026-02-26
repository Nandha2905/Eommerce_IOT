package com.Ecommerce.EcommereceWebApp.serv;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Ecommerce.EcommereceWebApp.entity.Cart;
import com.Ecommerce.EcommereceWebApp.entity.CartItem;
import com.Ecommerce.EcommereceWebApp.entity.Order;
import com.Ecommerce.EcommereceWebApp.entity.OrderAddress;
import com.Ecommerce.EcommereceWebApp.entity.OrderItem;
import com.Ecommerce.EcommereceWebApp.entity.Product;
import com.Ecommerce.EcommereceWebApp.entity.Users;
import com.Ecommerce.EcommereceWebApp.entity.UsersAddress;
import com.Ecommerce.EcommereceWebApp.repo.CartRepo;
import com.Ecommerce.EcommereceWebApp.repo.OrderRepo;
import com.Ecommerce.EcommereceWebApp.repo.ProductRepo;
import com.Ecommerce.EcommereceWebApp.repo.UserRepo;

@Service
@Transactional
public class OrderServ {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CartRepo cartRepo;

    public List<Order> getAllOrder() {
        return orderRepo.findAll();
    }

    public void deleteOrderById(Long id) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order " + id + " not found"));
        orderRepo.delete(order);
    }



    public void placeOrderDirect(Long prodId, int quantity,
                                 OrderAddress customAddress,
                                 String addressType,
                                 Long userId) {

        Users user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepo.findById(prodId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStock() < quantity) {
            throw new RuntimeException("Not enough stock for " + product.getProductName());
        }

        Order order = new Order();
        order.setUsers(user);
        order.setStatus("ORDER_PLACED");
        order.setOrderDate(LocalDateTime.now());

        OrderItem item = new OrderItem();
        item.setProductName(product.getProductName());
        item.setDescription(product.getDescription());
        item.setPrice(product.getPrice());    
        item.setSellerId(product.getUser().getId());
        item.setQuantity(quantity);            
        order.addItem(item);

        order.setTotal_amount(product.getPrice() * quantity);
        order.setOrder_add(buildOrderAddress(user, customAddress, addressType));

        orderRepo.save(order);

        product.setStock(product.getStock() - quantity);
        productRepo.save(product);
    }


    public void placeOrderByCart(Cart cart,
                                 OrderAddress customAddress,
                                 String addressType,
                                 Long userId) {

        if (cart.getCart_items().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Users user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUsers(user);
        order.setStatus("ORDER_PLACED");
        order.setOrderDate(LocalDateTime.now());

        double total = 0.0;

        for (CartItem cartItem : cart.getCart_items()) {

            Product product = cartItem.getProduct();

            if (product.getStock() < cartItem.getQuantity()) {
                throw new RuntimeException("Not enough stock for " + product.getProductName());
            }

            OrderItem item = new OrderItem();
            item.setProductName(product.getProductName());
            item.setDescription(product.getDescription());
            item.setPrice(cartItem.getPrice());      
            item.setSellerId(product.getUser().getId());
            item.setQuantity(cartItem.getQuantity()); 
            order.addItem(item);

            total += cartItem.getPrice() * cartItem.getQuantity();

            product.setStock(product.getStock() - cartItem.getQuantity());
            productRepo.save(product);
        }

        order.setTotal_amount(total);
        order.setOrder_add(buildOrderAddress(user, customAddress, addressType));

        orderRepo.save(order);

        cart.getCart_items().clear();
        cart.setTotal_amount(0.0);
        cartRepo.save(cart);
    }


     OrderAddress buildOrderAddress(Users user,
                                           OrderAddress custom,
                                           String type) {

        OrderAddress address = new OrderAddress();

        if ("CUSTOM".equalsIgnoreCase(type)) {
            address.setStreet(custom.getStreet());
            address.setCity(custom.getCity());
            address.setState(custom.getState());
            address.setPincode(custom.getPincode());
        } else {
            UsersAddress ua = user.getUsersAddress();
            address.setStreet(ua.getStreet());
            address.setCity(ua.getCity());
            address.setState(ua.getState());
            address.setPincode(ua.getPincode());
        }
        return address;     
    }
    public Order getOrderById(Long id)
    {
    	return orderRepo.findById(id).orElseThrow(() -> new RuntimeException("order"+id+" not found"));
    }
    public List<Order> getorderByUserid(Long userId)
    {
    		return orderRepo.findByUsers_Id(userId);
    }
    public List<Order> getRecentOrders() {
        return orderRepo.findTop5ByOrderByOrderDateDesc();
    }

    
}
