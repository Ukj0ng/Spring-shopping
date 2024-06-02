package com.uk.shopping.user.service;

import com.uk.shopping.common.constants.ErrorMessage;
import com.uk.shopping.user.dto.JoinRequestDto;
import com.uk.shopping.user.dto.LoginRequestDto;
import com.uk.shopping.user.handler.exception.LoginFailedException;
import com.uk.shopping.user.mapper.UserMapper;
import com.uk.shopping.user.model.User;
import com.uk.shopping.user.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    // 생성자를 통해 의존성 주입
    public UserService(UserRepository userRepository, UserMapper userMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void join(JoinRequestDto joinRequestDto) {
        User user = userMapper.toEntity(joinRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByUsername(loginRequestDto.getUsername());
        if (user != null && passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            return user;
        } else {
            throw new LoginFailedException(ErrorMessage.INVALID_USERNAME_OR_PASSWORD);
        }
    }
}
