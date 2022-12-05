package com.laundry.myApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laundry.myApp.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
	

}
