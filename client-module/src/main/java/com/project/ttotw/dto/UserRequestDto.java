package com.project.ttotw.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;

public class UserRequestDto {

    @Getter
    @Setter
    public static class SignIn {
        //이메일
        @NotBlank(message = "이메일을 입력해주세요.")
        private String email;
        //비밀번호
        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String password;

        public UsernamePasswordAuthenticationToken toAuthentication() {
            return new UsernamePasswordAuthenticationToken(email, password);
        }
    }

    @Getter
    @Setter
    public static class SignUp {

        //이메일
        @NotBlank(message = "이메일을 입력해주세요.")
        private String email;
        //비밀번호
        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String password;

    }
}
