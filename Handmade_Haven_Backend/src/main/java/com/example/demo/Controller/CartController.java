package com.example.demo.Controller;

import com.example.demo.Model.Cart;
import com.example.demo.Model.User;
import com.example.demo.Repository.CartRepo;
import com.example.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity; // ✅ THIS LINE FIXES THE ERROR
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
    public ResponseEntity<Cart> getCartByUserId(@PathVariable Long userId) {
        return userRepo.findById(userId)
                .map(user -> {
                    Cart cart = cartRepo.findByUser(user);
                    if (cart != null) {
                        return ResponseEntity.ok(cart);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new cart for a user (optional endpoint)
    @PostMapping("/create/{userId}")
    public ResponseEntity<Cart> createCart(@PathVariable Long userId) {
        return userRepo.findById(userId)
                .map(user -> {
                    // Check if cart already exists for user
                    Cart existingCart = cartRepo.findByUser(user);
                    if (existingCart != null) {
                        return ResponseEntity.status(409).body(existingCart); // Conflict, cart already exists
                    }
                    Cart cart = new Cart();
                    cart.setUser(user);
                    Cart savedCart = cartRepo.save(cart);
                    return ResponseEntity.status(201).body(savedCart);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
