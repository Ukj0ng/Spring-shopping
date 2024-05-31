package com.uk.shopping.user.service;

import com.uk.shopping.user.dto.JoinRequestDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Transactional
    public void join(JoinRequestDto joinRequestDto) {
        System.out.println("UserService join실행");
    }
}
