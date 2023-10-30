package com.service;

import java.util.List;
import java.util.Optional;

import com.advices.CartException;
import com.advices.UserNotFoundException;
import com.entity.User;

 

public interface UserService {

    public List<User> getAllAppUsers() throws Throwable;	
    public Optional<User> getAppUserById(int userId) throws UserNotFoundException  ;
    public User createAppUser(User user);
    public User updateAppUser(int userId, User updatedUser) ;
    public boolean deleteAppUser(int userId) throws UserNotFoundException;
    Optional<List<User>> getAppUserByUsername(String username) throws UserNotFoundException;
}
