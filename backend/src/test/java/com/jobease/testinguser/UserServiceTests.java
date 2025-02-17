package com.jobease.testinguser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jobease.dtos.UserDto;
import com.jobease.model.User;
import com.jobease.repository.UserRepository;
import com.jobease.service.UserService;

@SpringBootTest
public class UserServiceTests {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private UserDto userDto;
    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository, passwordEncoder);
        userDto = new UserDto("test@example.com", "password");
        user = new User("test@example.com", "encodedpassword");
    }

    // Register Tests
    @Test
    void testRegisterUser() {
        when(passwordEncoder.encode(userDto.getPassword())).thenReturn("encodedpassword");
        when(userRepository.save(any(User.class))).thenReturn(user);
        
        User result = userService.registerUser(userDto);
        assertNotNull(result);

        assertEquals("test@example.com", result.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testRegisterUser_EmailAlreadyExists() {
        when(passwordEncoder.encode(userDto.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenThrow(DataIntegrityViolationException.class);
    }

    // Login Tests
    @Test
    void testLoginUser() {
        when(userRepository.findByEmail(userDto.getEmail())).thenReturn(user);
        when(passwordEncoder.matches(userDto.getPassword(), user.getPassword())).thenReturn(true);

        User result = userService.loginUser(userDto);
        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
        verify(userRepository, times(1)).findByEmail(userDto.getEmail());
        verify(passwordEncoder, times(1)).matches(userDto.getPassword(), user.getPassword());
    }

    @Test
    void testLoginUser_InvalidCredentials() {
        when(userRepository.findByEmail(userDto.getEmail())).thenReturn(user);
        when(passwordEncoder.matches(userDto.getPassword(), user.getPassword())).thenReturn(false);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.loginUser(userDto);
        });
        assertEquals("Invalid email or password", exception.getMessage());
    }

    // Delete Tests
    @Test
    void testDeleteUser() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        User result = userService.deleteUser(user.getEmail());
        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void testDeleteUser_UserNotFound() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(null);
        User result = userService.deleteUser(user.getEmail());
        assertNull(result);
        verify(userRepository, never()).delete(any(User.class));
    }
};
