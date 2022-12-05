package com.laundry.myApp.controllers.form;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Basic;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


public class RegistrationFormDto {

    @NotNull
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @Email(message = "invalid e-mail")
    private String email;

    @NotEmpty(message = "The username must be informed")
    @Size(min = 4, message = "Your username must be at least 4 characters long")
    private String username;

    @NotEmpty(message = "Your password must be informed")
    @Size(min = 3, message = "Your password must be at least 3 characters long")
    private String password;

    @Basic
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;
    
    //getters and setters


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
    
    
    

}
