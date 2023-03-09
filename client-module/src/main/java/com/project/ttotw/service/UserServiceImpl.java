package com.project.ttotw.service;

import com.project.ttotw.dto.ApiResponse;
import com.project.ttotw.dto.UserRequestDto;
import com.project.ttotw.dto.UserResponseDto;
import com.project.ttotw.jwt.JwtTokenProvider;
import com.project.ttotw.lib.Helper;
import com.project.ttotw.redis.RefreshToken;
import com.project.ttotw.repository.UserRepository;
import com.project.ttotw.repository.redis.RefreshTokenRedisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RefreshTokenRedisRepository refreshTokenRedisRepository;

    private final ApiResponse response;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public ResponseEntity<?> signin(HttpServletRequest request, UserRequestDto.SignIn signIn) {
        // 1. email, password 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = signIn.toAuthentication();

        // 2. 실제 검증 (사용자 비밀번호 확인)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT Token 생성
        UserResponseDto.TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        // 4. Redis RefreshToken 저장
        refreshTokenRedisRepository.save(RefreshToken.builder()
                .id(authentication.getName())
                .ip(Helper.getClientIp(request))
                .authorities(authentication.getAuthorities())
                .refreshToken(tokenInfo.getRefreshToken())
                .build());

        return response.success(tokenInfo);
    }

    public ResponseEntity<?> reissue(HttpServletRequest request) {
        //1. Request Header 에서 JWT Token 추출
        String token = jwtTokenProvider.resolveToken(request);

        //2. validateToken 메서드로 토큰 유효성 검사
        if (token != null && jwtTokenProvider.validateToken(token)) {
            //3. refresh token 인지 확인
            if (jwtTokenProvider.isRefreshToken(token)) {
                //refresh token
                RefreshToken refreshToken = refreshTokenRedisRepository.findByRefreshToken(token);
                if (refreshToken != null) {
                    //4. 최초 로그인한 ip 와 같은지 확인 (처리 방식에 따라 재발급을 하지 않거나 메일 등의 알림을 주는 방법이 있음)
                    String currentIpAddress = Helper.getClientIp(request);
                    if (refreshToken.getIp().equals(currentIpAddress)) {
                        // 5. Redis 에 저장된 RefreshToken 정보를 기반으로 JWT Token 생성
                        UserResponseDto.TokenInfo tokenInfo = jwtTokenProvider.generateToken(refreshToken.getId(), refreshToken.getAuthorities());

                        // 4. Redis RefreshToken update
                        refreshTokenRedisRepository.save(RefreshToken.builder()
                                .id(refreshToken.getId())
                                .ip(currentIpAddress)
                                .authorities(refreshToken.getAuthorities())
                                .refreshToken(tokenInfo.getRefreshToken())
                                .build());

                        return response.success(tokenInfo);
                    }
                }
            }
        }

        return response.fail("토큰 갱신에 실패했습니다.");
    }

    public ResponseEntity<?> signup(UserRequestDto.SignUp signUp) {

        //TODO:: email 중복 불가 (+ 프론트 단에서 중복 여부 체크 하도록 api 추가)

        return null;
    }
}
