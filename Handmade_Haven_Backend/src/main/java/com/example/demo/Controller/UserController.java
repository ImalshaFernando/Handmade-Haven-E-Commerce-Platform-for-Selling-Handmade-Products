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

    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getRole(), user.getAddress(), user.Phone());
    }

    @GetMapping("/")
    public List<UserDTO> getAllUsers() {
        return userRepo.findAll().stream().map(this::convertToDTO).toList();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return userRepo.findById(id)
            .map(user -> ResponseEntity.ok(convertToDTO(user)))
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<UserDTO> getUserByName(@PathVariable String name) {
        return userRepo.findByName(name)
            .map(user -> ResponseEntity.ok(convertToDTO(user)))
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<UserDTO> getUserByPhone(@PathVariable int phone) {
        return userRepo.findByPhone(phone)
            .map(user -> ResponseEntity.ok(convertToDTO(user)))
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/address/{address}")
    public ResponseEntity<UserDTO> getUserByAddress(@PathVariable String address) {
        return userRepo.findByAddress(address)
            .map(user -> ResponseEntity.ok(convertToDTO(user)))
            .orElse(ResponseEntity.notFound().build());
    }

    // Prefer POST with body for email search to avoid logging sensitive info
    @PostMapping("/findByEmail")
    public ResponseEntity<UserDTO> getUserByEmail(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        return userRepo.findByEmail(email)
            .map(user -> ResponseEntity.ok(convertToDTO(user)))
            .orElse(ResponseEntity.notFound().build());
    }
}
