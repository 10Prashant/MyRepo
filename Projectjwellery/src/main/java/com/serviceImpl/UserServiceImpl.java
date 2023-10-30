package com.serviceImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.advices.CartException;
import com.advices.UserNotFoundException;
import com.entity.Cart;
import com.entity.User;
import com.repository.UserRepository;
import com.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllAppUsers() throws Throwable{
    	List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new CartException("No Users found");
        }
        return users;
    }

    @Override
    public Optional<User> getAppUserById(int userId) throws UserNotFoundException {
    	Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new UserNotFoundException("Cart with ID " + userId + " not found");
        }
        return user;
    }

    @Override
    public User createAppUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateAppUser(int userId, User updatedUser) {
        if (!userRepository.existsById(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AppUser not found");
        }
        updatedUser.setUserId(userId);
        return userRepository.save(updatedUser);
    }

    @Override
    public boolean deleteAppUser(int userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public Optional<List<User>> getAppUserByUsername(String username) throws UserNotFoundException {
        Optional<List<User>> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user;
        } else {
            throw new UserNotFoundException("User with username " + username + " not found");
        }
    }
}