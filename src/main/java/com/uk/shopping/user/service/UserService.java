package com.uk.shopping.user.service;

import com.uk.shopping.user.dto.JoinRequestDto;
import com.uk.shopping.user.dto.LoginRequestDto;
import com.uk.shopping.user.mapper.UserMapper;
import com.uk.shopping.user.model.User;
import com.uk.shopping.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
}
