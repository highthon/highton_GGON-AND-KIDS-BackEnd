package com.ggonandkids.zzol.global.interceptor;

import com.ggonandkids.zzol.domain.user.Member;
import com.ggonandkids.zzol.global.annotation.AuthToken;
import com.ggonandkids.zzol.global.jwt.TokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
@RequiredArgsConstructor
public class MyInterceptor implements HandlerInterceptor {

    private final TokenProvider tokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {
        log.info("[{}] request uri: {}", request.getMethod(), request.getRequestURI());
        log.info("status : {}", response.getStatus());
        if (!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        }

        if (!handlerMethod.getMethod().isAnnotationPresent(AuthToken.class)) {
            return true;
        }

        String token = request.getHeader("Authorization");
        if (token.equals("")) {
            throw new Member.UnAuthenticationException();
        }
        log.info("{}", token);
        Member member = tokenProvider.validateToken(token);
        request.setAttribute("member", member);

        return true;
    }
}
