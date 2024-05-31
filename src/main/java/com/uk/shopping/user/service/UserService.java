package com.uk.shopping.user.service;

import com.uk.shopping.user.dto.JoinRequestDto;
import com.uk.shopping.user.mapper.UserMapper;
import com.uk.shopping.user.model.User;
import com.uk.shopping.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    // 생성자를 통해 의존성 주입
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public void join(JoinRequestDto joinRequestDto) {
        User user = userMapper.toEntity(joinRequestDto);
        userRepository.save(user);
    }
}
