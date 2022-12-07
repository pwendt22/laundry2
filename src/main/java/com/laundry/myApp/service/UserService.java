
  package com.laundry.myApp.service;
  
  import com.laundry.myApp.dto.UserDto;
 import com.laundry.myApp.models.User;
  
  
  import java.util.List;
  
  public interface UserService {
  
 void saveUser(UserDto userDto);
	  
  User findUserByEmail(String email);
  
   
  User findUserByUsername(String username);
  
  List<UserDto> findAllUsers(); }
 