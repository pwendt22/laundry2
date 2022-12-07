package com.laundry.myApp.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String role;
	
	
	@ManyToMany(mappedBy="roles",fetch=FetchType.EAGER)
	private List<User> users;
		
	
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Role(String role) {
		super();
		this.role = role;
	}
	

	//getters ad setters
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	

}