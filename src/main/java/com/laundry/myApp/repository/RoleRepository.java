package com.laundry.myApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laundry.myApp.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	//method to verify if a role already exist
	Role findByRole(String role);


}
