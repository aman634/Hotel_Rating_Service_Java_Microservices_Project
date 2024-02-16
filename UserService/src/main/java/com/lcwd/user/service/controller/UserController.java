package com.lcwd.user.service.controller;

import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;

import com.lcwd.user.service.exception.ResourceNotFound;
import com.lcwd.user.service.services.Userservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
   @Autowired
   Userservices userservices;

    @PostMapping()
    public ResponseEntity<User> addUser(@RequestBody User user){
        User user1=userservices.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(){
        List users= userservices.getAllUser();
        return ResponseEntity.status(HttpStatus.FOUND).body(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) throws ResourceNotFound {
       User user1= userservices.getUserById(userId);
       return ResponseEntity.status(HttpStatus.FOUND).body(user1);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId){
        userservices.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
