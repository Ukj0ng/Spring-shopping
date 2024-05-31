package com.uk.shopping.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class JoinRequestDto {

    @NotBlank(message = "사용자 이름은 필수 항목입니다.")
    @Size(min = 4, max = 30, message = "사용자 이름은 4자 이상 30자 이하로 설정해야 합니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 항목입니다.")
    @Size(min = 8, max = 100, message = "비밀번호는 8자 이상 100자 이하로 설정해야 합니다.")
    private String password;

    @NotBlank(message = "이름은 필수 항목입니다.")
    @Size(min = 1, max = 50, message = "이름은 1자 이상 50자 이하로 설정해야 합니다.")
    private String name;

    @Email(message = "유효하지 않은 이메일 형식입니다.")
    private String email;
}
