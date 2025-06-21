package com.example.demo.Controller;

import com.example.demo.Model.CartItem;
import com.example.demo.Repository.CartItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart-items")
public class CartItemController {

    @Autowired
    private CartItemRepo cartItemRepo;

    @GetMapping
    public ResponseEntity<List<CartItem>> getAllCartItems() {
        return ResponseEntity.ok(cartItemRepo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable("id") Long id) {
        return cartItemRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CartItem> createCartItem(@RequestBody CartItem cartItem) {
        CartItem savedItem = cartItemRepo.save(cartItem);
        return ResponseEntity.status(201).body(savedItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable("id") Long id, @RequestBody CartItem updatedItem) {
        return cartItemRepo.findById(id)
                .map(item -> {
                    item.setQuantity(updatedItem.getQuantity());
                    // optionally update product or cart if needed
                    CartItem savedItem = cartItemRepo.save(item);
                    return ResponseEntity.ok(savedItem);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long id) {
        if (cartItemRepo.existsById(id)) {
            cartItemRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
