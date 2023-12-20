package com.example.banktest.service;

import com.example.banktest.model.User;
import com.example.banktest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User userRegister(String username, String password){
        if (this.userRepository.findUserByUsername(username) != null)
            throw new IllegalArgumentException("Username is already taken!");

        //create new user
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        return this.userRepository.save(user);
    }

    public List<User> getAllUsers(){
        if (this.userRepository.findAll().isEmpty())
            throw new IllegalArgumentException("No Users Found!!");
        return this.userRepository.findAll();
    }
}
