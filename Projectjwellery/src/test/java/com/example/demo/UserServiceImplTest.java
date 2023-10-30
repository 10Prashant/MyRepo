package com.example.demo;

import com.advices.UserNotFoundException;
import com.entity.User;
import com.repository.UserRepository;
import com.serviceImpl.UserServiceImpl;

 

 

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

 

 

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

 

 

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

 

 

class UserServiceImplTest {

 

 

    @Mock
    private UserRepository userRepository;

 

 

    @InjectMocks
    private UserServiceImpl userService;

 

 

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

 

 

    @Test
    void testGetAllAppUsers() throws Throwable {
        // Arrange
        List<User> mockUsersList = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(mockUsersList);

 

 

        // Act
        List<User> result = userService.getAllAppUsers();

 

 

        // Assert
        assertEquals(mockUsersList, result);
    }

 

 

    @Test
    void testGetAppUserById() throws UserNotFoundException {
        // Arrange
        int userId = 1;
        User mockUser = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

 

 

        // Act
        Optional<User> result = userService.getAppUserById(userId);

 

 

        // Assert
        assertTrue(result.isPresent());
        assertEquals(mockUser, result.get());
    }

 

 

    @Test
    void testCreateAppUser() {
        // Arrange
        User mockUser = new User();
        when(userRepository.save(any(User.class))).thenReturn(mockUser);

 

 

        // Act
        User result = userService.createAppUser(mockUser);

 

 

        // Assert
        assertEquals(mockUser, result);
    }

 

 

    @Test
    void testUpdateAppUser() {
        // Arrange
        int userId = 1;
        User mockUser = new User();
        when(userRepository.existsById(userId)).thenReturn(true);
        when(userRepository.save(any(User.class))).thenReturn(mockUser);

 

 

        // Act
        User updatedUser = userService.updateAppUser(userId, mockUser);

 

 

        // Assert
        assertEquals(mockUser, updatedUser);
    }

 

 

    @Test
    void testDeleteAppUser() {
        // Arrange
        int userId = 1;
        when(userRepository.existsById(userId)).thenReturn(true);

 

 

        // Act
        boolean deleted = userService.deleteAppUser(userId);

 

 

        // Assert
        assertTrue(deleted);
        verify(userRepository, times(1)).deleteById(userId);
    }
}
