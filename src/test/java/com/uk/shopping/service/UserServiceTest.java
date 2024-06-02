package com.uk.shopping.service;

import com.uk.shopping.user.dto.JoinRequestDto;
import com.uk.shopping.user.dto.LoginRequestDto;
import com.uk.shopping.user.handler.exception.LoginFailedException;
import com.uk.shopping.user.mapper.UserMapper;
import com.uk.shopping.user.model.User;
import com.uk.shopping.user.repository.UserRepository;
import com.uk.shopping.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void join_should_save_user() {
        // Arrange
        JoinRequestDto joinRequestDto = new JoinRequestDto();
        joinRequestDto.setUsername("testuser");
        joinRequestDto.setPassword("testpassword");

        User user = new User();
        user.setUsername("testuser");
        user.setPassword("encodedpassword");

        when(userMapper.toEntity(joinRequestDto)).thenReturn(user);
        when(passwordEncoder.encode("testpassword")).thenReturn("encodedpassword");

        // Act
        userService.join(joinRequestDto);

        // Assert
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void login_should_return_user_when_credentials_are_valid() {
        // Arrange
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setUsername("testuser");
        loginRequestDto.setPassword("testpassword");

        User user = new User();
        user.setUsername("testuser");
        user.setPassword("encodedpassword");

        when(userRepository.findByUsername("testuser")).thenReturn(user);
        when(passwordEncoder.matches("testpassword", "encodedpassword")).thenReturn(true);

        // Act
        User loggedInUser = userService.login(loginRequestDto);

        // Assert
        assertNotNull(loggedInUser);
        assertEquals("testuser", loggedInUser.getUsername());
    }

    @Test
    void login_should_throw_exception_when_credentials_are_invalid() {
        // Arrange
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setUsername("testuser");
        loginRequestDto.setPassword("invalidpassword");

        when(userRepository.findByUsername("testuser")).thenReturn(null);

        // Act and Assert
        assertThrows(LoginFailedException.class, () -> userService.login(loginRequestDto));
    }
}
