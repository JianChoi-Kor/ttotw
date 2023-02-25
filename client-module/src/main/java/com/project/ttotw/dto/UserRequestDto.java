package com.project.ttotw.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class UserRequestDto {

    @Getter
    @Setter
    public static class Login {
        //이메일
        @NotBlank(message = "이메일을 입력해주세요.")
        private String email;
        //비밀번호
        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String password;
    }
}
