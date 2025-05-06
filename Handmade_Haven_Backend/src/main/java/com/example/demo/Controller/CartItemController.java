package com.example.demo.Controller;

import com.example.demo.Model.CartItem;
import com.example.demo.Repository.CartItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    @Autowired
    private CartItemRepo cartitemRepo;

    @GetMapping
    public List<CartItem> getAllCartItems() {
        return cartitemRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<CartItem> getCartItemById(@PathVariable Long id) {
        return cartitemRepo.findById(id);
    }

    @PostMapping
    public CartItem createCartItem(@RequestBody CartItem cartItem) {
        return cartitemRepo.save(cartItem);
    }

    @PutMapping("/{id}")
    public CartItem updateCartItem(@PathVariable Long id, @RequestBody CartItem updatedItem) {
        return cartitemRepo.findById(id)
                .map(item -> {
                    item.setQuantity(updatedItem.getQuantity());
                    // you may also allow updating product or cart if needed
                    return cartitemRepo.save(item);
                })
                .orElseThrow(() -> new RuntimeException("CartItem not found with id " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteCartItem(@PathVariable Long id) {
    	cartitemRepo.deleteById(id);
    }
}
