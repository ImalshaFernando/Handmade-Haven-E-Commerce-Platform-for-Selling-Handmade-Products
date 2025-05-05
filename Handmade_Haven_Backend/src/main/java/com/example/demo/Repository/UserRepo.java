package com.example.demo.Repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.demo.Model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	Optional<User> findByName(String name);
	Optional<User> findByEmail(String email);
	Optional<User> findByPhone(int phone);
	Optional<User> findByAddress(String address);

	
}
