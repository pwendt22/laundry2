package com.laundry.myApp.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.laundry.myApp.models.Role;
import com.laundry.myApp.repository.RoleRepository;


@Component
public class LoadData implements CommandLineRunner {
	
	@Autowired
	private RoleRepository roleRepository;


	@Override
	public void run(String... args) throws Exception {
		String[] roles = {"ADMIN", "USER"};
		for (String roleString: roles) {
			Role role = roleRepository.findByRole(roleString);
			if (role == null) {
				role = new Role(roleString);
				roleRepository.save(role);	
	
			}
			
		}
	}

}
