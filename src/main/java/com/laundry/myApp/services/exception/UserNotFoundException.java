package com.laundry.myApp.services.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super("User with username: " + username + " cannot be founded!");
    }
}
