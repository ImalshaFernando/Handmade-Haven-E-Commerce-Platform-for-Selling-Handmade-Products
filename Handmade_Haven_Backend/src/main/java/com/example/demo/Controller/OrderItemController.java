package com.example.demo.Controller;

import com.example.demo.Model.OrderItem;
import com.example.demo.Repository.OrderItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemRepo orderitemRepo;

    @GetMapping
    public List<OrderItem> getAllOrderItems() {
        return orderitemRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<OrderItem> getOrderItemById(@PathVariable Long id) {
        return orderitemRepo.findById(id);
    }

    @PostMapping
    public OrderItem createOrderItem(@RequestBody OrderItem orderItem) {
        return orderitemRepo.save(orderItem);
    }

    @PutMapping("/{id}")
    public OrderItem updateOrderItem(@PathVariable Long id, @RequestBody OrderItem updatedItem) {
        return orderitemRepo.findById(id)
                .map(item -> {
                    item.setQuantity(updatedItem.getQuantity());
                    item.setPrice(updatedItem.getPrice());
                    return orderitemRepo.save(item);
                })
                .orElseThrow(() -> new RuntimeException("OrderItem not found with id " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteOrderItem(@PathVariable Long id) {
    	orderitemRepo.deleteById(id);
    }
}
