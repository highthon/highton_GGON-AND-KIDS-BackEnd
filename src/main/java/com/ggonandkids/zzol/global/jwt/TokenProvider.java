package com.ggonandkids.zzol.global.jwt;

import com.ggonandkids.zzol.domain.user.Member;
import com.ggonandkids.zzol.domain.user.domain.repository.MemberRepository;
import com.ggonandkids.zzol.global.config.AppProperties;
import com.ggonandkids.zzol.global.exception.GlobalException;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenProvider {
    private static final long JWT_ACCESS_EXPIRE = 1000 * 60 * 60 * 24 * 7;
    private static final long JWT_REFRESH_EXPIRE = 1000L * 60 * 60 * 24 * 30;
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256; //해쉬암호 HS256
    private final AppProperties appProperties;
    private final MemberRepository userRepository;

    //AccessToken, RefreshToken 생성
    public String generateToken(Long memberId, JwtAuth jwtAuth) {
        Date expiration = new Date();
        expiration = (jwtAuth == JwtAuth.ACCESS_TOKEN)
                ? new Date(expiration.getTime() + JWT_ACCESS_EXPIRE)
                : new Date(expiration.getTime() + JWT_REFRESH_EXPIRE);
        String secretKey = (jwtAuth == JwtAuth.ACCESS_TOKEN)
                ? appProperties.getSecret()
                : appProperties.getRefreshSecret();

        Claims claims = Jwts.claims();
        claims.put("memberId", memberId);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(jwtAuth.toString())
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .signWith(SIGNATURE_ALGORITHM, secretKey)
                .compact();
    }

    private Claims parseToken(String token, JwtAuth jwtAuth) {
        try {
            return Jwts.parser()
                    .setSigningKey((jwtAuth == JwtAuth.ACCESS_TOKEN)
                            ? appProperties.getSecret()
                            : appProperties.getRefreshSecret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new GlobalException(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다.");
        } catch (SignatureException | MalformedJwtException e) {
            throw new GlobalException(HttpStatus.UNAUTHORIZED, "위조된 토큰입니다.");
        } catch (IllegalArgumentException e) {
            throw new GlobalException(HttpStatus.BAD_REQUEST, "토큰이 존재하지 않습니다.");
        } catch (Exception e) {
            throw new GlobalException(HttpStatus.INTERNAL_SERVER_ERROR, "토큰 서비스와의 오류가 발생하였습니다.");
        }
    }

    public Member validateToken(String token) {
        return userRepository.findById(
                        Long.valueOf(parseToken(token, JwtAuth.ACCESS_TOKEN)
                                .get("memberId")
                                .toString())
                )
                .orElseThrow(Member.NotFoundException::new);
    }
}

