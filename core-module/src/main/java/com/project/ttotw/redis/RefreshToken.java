package com.project.ttotw.redis;

import com.project.ttotw.config.ExpireTime;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "refresh", timeToLive = ExpireTime.REFRESH_TOKEN_EXPIRE_TIME)
public class RefreshToken {

    private String id;

    private String ip;

    private Collection<? extends GrantedAuthority> authorities;

    @Indexed
    private String refreshToken;
}
