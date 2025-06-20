package com.example.demo.Controller;

import com.example.demo.Model.Cart;
import com.example.demo.Model.User;
import com.example.demo.Repository.CartRepo;
import com.example.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
        Optional<User> userOptional = userRepo.findById(userId);

        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Cart cart = cartRepo.findByUser(userOptional.get());

        if (cart == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cart);
    }

    // Create a new cart for a user
    @PostMapping("/create/{userId}")
    public ResponseEntity<?> createCart(@PathVariable Long userId) {
        Optional<User> userOptional = userRepo.findById(userId);

        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = userOptional.get();
        Cart existingCart = cartRepo.findByUser(user);

        if (existingCart != null) {
            return ResponseEntity.status(409).body("Cart already exists for this user.");
        }

        Cart newCart = new Cart();
        newCart.setUser(user);
        Cart savedCart = cartRepo.save(newCart);

        return ResponseEntity.status(201).body(savedCart);
    }
}