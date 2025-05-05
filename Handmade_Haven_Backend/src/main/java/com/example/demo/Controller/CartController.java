package com.example.demo.Controller;

import com.example.demo.Model.Cart;
import com.example.demo.Model.User;
import com.example.demo.Repository.CartRepo;
import com.example.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private UserRepo userRepo;

    // Get cart by user ID
    @GetMapping("/user/{userId}")
    public Cart getCartByUserId(@PathVariable Long userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user != null) {
            return cartRepo.findByUser(user);
        }
        return null;
    }

    // Create a new cart for a user (optional endpoint)
    @PostMapping("/create/{userId}")
    public Cart createCart(@PathVariable Long userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user != null) {
            Cart cart = new Cart();
            cart.setUser(user);
            return cartRepo.save(cart);
        }
        return null;
    }
}
