package com.revature.service;

import com.revature.exception.InvalidLoginException;
import com.revature.model.User;
import com.revature.repository.UserRepository;

import java.sql.SQLException;

public class AuthService {

    private UserRepository userRepo = new UserRepository();

    public User login(String username, String password) throws SQLException, InvalidLoginException {
        // userRepo.getUserByUsernameAndPasword could return a null value or non-null
        // null = invalid credentials were provided
        // non-null = valid credentials were provided
        User user = userRepo.getUserByUsernameAndPassword(username, password);

        // There's two things that a method might do
        // 1. successfully return a value
        // 2. throw an exception and not return anything
        if (user == null) { // did not successfully log in
            throw new InvalidLoginException("Invalid username and/or password");
        }

        return user;
    }

}
