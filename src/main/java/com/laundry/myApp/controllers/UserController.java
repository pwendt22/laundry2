package com.laundry.myApp.controllers;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.laundry.myApp.dto.UserDto;
import com.laundry.myApp.models.Role;
import com.laundry.myApp.models.User;
import com.laundry.myApp.repository.RoleRepository;
import com.laundry.myApp.repository.UserRepository;
//import com.laundry.myApp.service.UserService;
import com.laundry.myApp.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	  private UserService userService;
	 
	  public UserController(UserService userService) { this.userService =
	  userService; }
	 

	// handler method to handle login request
	@GetMapping("/login")
	public String loginForm(){
		return "login";
	}

	@GetMapping("/new")
	public String addUser(Model model) {
		model.addAttribute("user", new User());
		return "/register";
	}
	   @PostMapping("/save")
	    public String registration(@Valid @ModelAttribute("user") UserDto user,
	                               BindingResult result,
	                               Model model){
	        User existing = userService.findUserByEmail(user.getEmail());
	        if (existing != null) {
	            result.rejectValue("email", null, "There is already an account registered with that email");
	        }
	        if (result.hasErrors()) {
	            model.addAttribute("user", user);
	            return "/register";
	        }
	        userService.saveUser(user);
	      //  attributes.addFlashAttribute("message", "Information added succesfully!");
			return "redirect:/user/new";
	   
	   }



		
		
		
	

	// method to bring the user list in the admin html
	@RequestMapping("/admin/list")
	public String userList(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "/auth/admin/admin-user-list";

	}

	// method to delete a user by the admin
	@GetMapping("/admin/delete/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));
		userRepository.delete(user);
		return "redirect:/user/admin/list";
	}

	// method to edit a user by the admin

	@GetMapping("/edit/{id}")
	public String editUser(@PathVariable("id") long id, Model model) {
		Optional<User> oldUser = userRepository.findById(id);
		if (!oldUser.isPresent()) {
			throw new IllegalArgumentException("Invalid user:" + id);
		}
		User user = oldUser.get();
		model.addAttribute("user", user);
		return "/auth/user/user-edit";

	}

	@PostMapping("/edit/{id}")
	public String editUser(@PathVariable("id") long id, @Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			user.setId(id);
			return "/auth/user/user-edit";
		}
		userRepository.save(user);
		return "redirect:/user/admin/list";
	}

	@GetMapping("/editRole/{id}")
	public String selectRole(@PathVariable("id") long id, Model model) {
		Optional<User> oldUser = userRepository.findById(id);
		if (!oldUser.isPresent()) {
			throw new IllegalArgumentException("Invalid user" + id);
		}
		User user = oldUser.get();
		model.addAttribute("user", user);

		model.addAttribute("listRoles", roleRepository.findAll());

		return "/auth/admin/edit-user-role";
	}

	@PostMapping("/editRole/{id}")
	public String assignRole(@PathVariable("id") long idUser, @RequestParam(value = "pps", required = false) int[] pps,
			User user, RedirectAttributes attributes) {
		if (pps == null) {
			user.setId(idUser);
			attributes.addFlashAttribute("message", "At leat one role has to be assign");
			return "redirect:/user/editRole/" + idUser;
		} else {
			// Bring the role list selected by the user
			List<Role> roles = new ArrayList<Role>();
			for (int i = 0; i < pps.length; i++) {
				long idRole = pps[i];
				Optional<Role> roleOptional = roleRepository.findById(idRole);
				if (roleOptional.isPresent()) {
					Role role = roleOptional.get();
					roles.add(role);
				}
			}
			Optional<User> userOptional = userRepository.findById(idUser);
			if (userOptional.isPresent()) {
				User usr = userOptional.get();
				usr.setRoles(roles); // set roles to the users
				usr.setEnable(user.isEnable());
				userRepository.save(usr);
			}
		}
		return "redirect:/user/admin/list";
	}

}// end main class
