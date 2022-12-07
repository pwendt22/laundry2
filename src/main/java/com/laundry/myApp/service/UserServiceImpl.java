
  package com.laundry.myApp.service;
  
  import java.util.Arrays; 
  import java.util.List; 
  import java.util.stream.Collectors;
  
  import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.laundry.myApp.dto.UserDto; 
  import com.laundry.myApp.models.Role;
  import com.laundry.myApp.models.User; 
  import com.laundry.myApp.repository.RoleRepository; 
  import   com.laundry.myApp.repository.UserRepository;

  import com.laundry.myApp.service.UserService;
  
  @Service 
  public class UserServiceImpl implements UserService {
  
  private UserRepository userRepository; 
  private RoleRepository roleRepository;
  private PasswordEncoder passwordEncoder;
  
  public UserServiceImpl(UserRepository userRepository, 
		  RoleRepository  roleRepository, 
		  PasswordEncoder passwordEncoder)
  { this.userRepository =  userRepository; 
  this.roleRepository = roleRepository; 
  this.passwordEncoder =  passwordEncoder; }
  
  @Override public void saveUser(UserDto userDto) { 
	  User user = new User();
  user.setUsername(userDto.getUsername()); 
  user.setEmail(userDto.getEmail());
  
  //encrypt the password once we integrate spring security
  //user.setPassword(userDto.getPassword());
  user.setPassword(passwordEncoder.encode(userDto.getPassword())); 
  Role role =  roleRepository.findByRole("ADMIN"); 
  if(role == null){ role =  checkRoleExist(); }
  user.setRoles(Arrays.asList(role));
  userRepository.save(user); }
  
  @Override public User findUserByUsername(String username) { return
  userRepository.findByUsername(username); }
  
  @Override public List<UserDto> findAllUsers() { 
	  List<User> users = userRepository.findAll();
	  return users.stream().map((user) -> convertEntityToDto(user)) .collect(Collectors.toList()); }
  
  private UserDto convertEntityToDto(User user){
	  UserDto userDto = new  UserDto(); 
	  String[] username = user.getUsername().split(" ");
	  userDto.setName(username[0]);
	  userDto.setEmail(user.getEmail());
	  return userDto;
  }
  
  private Role checkRoleExist() { 
	  Role role = new Role();
	  role.setRole("ADMIN"); 
  	return roleRepository.save(role); }

@Override
public User findUserByEmail(String email) {
	// TODO Auto-generated method stub
	return null;
}

}
  
  
  
 