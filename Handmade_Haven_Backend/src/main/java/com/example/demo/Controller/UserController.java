package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    // Get all users
    @GetMapping("/")
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    // Get user by ID
    @GetMapping("/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return userRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get user by name
    @GetMapping("/name/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable("name") String name) {
        return userRepo.findByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get user by phone
    @GetMapping("/phone/{phone}")
    public ResponseEntity<User> getUserByPhone(@PathVariable("phone") String phone) {
        return userRepo.findByPhone(phone)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get user by address
    @GetMapping("/address/{address}")
    public ResponseEntity<User> getUserByAddress(@PathVariable("address") String address) {
        return userRepo.findByAddress(address)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get user by email (using POST to avoid exposing email in logs)
    @PostMapping("/findByEmail")
    public ResponseEntity<User> getUserByEmail(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        return userRepo.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}