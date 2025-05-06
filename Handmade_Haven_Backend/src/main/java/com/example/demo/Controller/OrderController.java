package com.example.demo.Controller;

import com.example.demo.Model.Order;
import com.example.demo.Repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepo orderrepo;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderrepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Order> getOrderById(@PathVariable Long id) {
        return orderrepo.findById(id);
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderrepo.save(order);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        return orderrepo.findById(id)  
                .map(order -> {
                    order.setOrderDate(updatedOrder.getOrderDate());
                    order.setStatus(updatedOrder.getStatus());
                    order.setTotalPrice(updatedOrder.getTotalPrice());
                    return orderrepo.save(order);
                })
                .orElseThrow(() -> new RuntimeException("Order not found with id " + id));
    }


    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderrepo.deleteById(id);
    }
}
