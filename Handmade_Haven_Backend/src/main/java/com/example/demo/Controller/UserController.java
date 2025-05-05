package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepo;

import java.util.List;
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
    public Optional<User> getUserById(@PathVariable Long id) {
        return userRepo.findById(id);
    }

    // Get user by name
    @GetMapping("/name/{name}")
    public Optional<User> getUserByName(@PathVariable String name) {
        return userRepo.findByName(name);
    }
    
    // Get user by email
    @GetMapping("/email/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email) {
        return userRepo.findByEmail(email);
    }
    

    // Get user by phone
    @GetMapping("/phone/{phone}")
    public Optional<User> getUserByPhone(@PathVariable int phone) {
        return userRepo.findByPhone(phone);
    }
    

    // Get user by address
    @GetMapping("/address/{address}")
    public Optional<User> getUserByAddress(@PathVariable String address) {
        return userRepo.findByAddress(address);
    }
}
