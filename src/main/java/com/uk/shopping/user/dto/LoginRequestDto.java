package com.uk.shopping.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDto {

    @NotBlank(message = "사용자 이름은 필수 항목입니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 항목입니다.")
    private String password;
}
