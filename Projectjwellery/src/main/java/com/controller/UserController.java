package com.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.advices.UserNotFoundException;
import com.entity.User;
import com.service.UserService;

@RestController
@RequestMapping("/api/appusers")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getusers")
    public ResponseEntity<List<User>> getAllAppUsers() throws Throwable{
        List<User> appUsers = userService.getAllAppUsers();
        return ResponseEntity.ok(appUsers);
    }

    @GetMapping("/getuserbyid/{userId}")
    public ResponseEntity<User> getAppUserById(@PathVariable int userId) throws Throwable{
        Optional<User> appUser = userService.getAppUserById(userId);
        if (appUser.isPresent()) {
            return ResponseEntity.ok(appUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/createuser")
    public ResponseEntity<User> createAppUser(@RequestBody User user) throws Throwable{
        User createdUser = userService.createAppUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/updateuserbyid/{userId}")
    public ResponseEntity<User> updateAppUser(@PathVariable int userId, @RequestBody User updatedAppUser) throws Throwable{
        User updated = userService.updateAppUser(userId, updatedAppUser);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteuserbyid/{userId}")
    public ResponseEntity<Void> deleteAppUser(@PathVariable int userId) throws Throwable{
        boolean deleted = userService.deleteAppUser(userId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/getuserbyusername/{username}")
    public ResponseEntity<List<User>> getAppUserByUsername(@PathVariable String username) {
        try {
            Optional<List<User>> user = userService.getAppUserByUsername(username);
            return ResponseEntity.ok(user.orElseThrow());
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
