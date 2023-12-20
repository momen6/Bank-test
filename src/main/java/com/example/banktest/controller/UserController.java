package com.example.banktest.controller;

import com.example.banktest.model.User;
import com.example.banktest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> fetchAllUsers(){
        return new ResponseEntity<>(this.userService.getAllUsers(),HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestParam String username, @RequestParam String password){
        User user = this.userService.userRegister(username,password);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}
