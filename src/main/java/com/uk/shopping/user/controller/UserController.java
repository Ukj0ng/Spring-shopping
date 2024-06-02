package com.uk.shopping.user.controller;

import com.uk.shopping.user.dto.JoinRequestDto;
import com.uk.shopping.user.dto.LoginRequestDto;
import com.uk.shopping.user.model.User;
import com.uk.shopping.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("join")
    public String join() {
        return "user/join";
    }

    @PostMapping("join")
    public String join(@Valid @RequestBody JoinRequestDto joinRequestDto) {
        userService.join(joinRequestDto);
        return "index";
    }

    @GetMapping("login")
    public String login() {
        return "user/login";
    }

    @PostMapping("login")
    public String login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        User user = userService.login(loginRequestDto);
        System.out.println(user.toString());
        return "index";
    }
}
