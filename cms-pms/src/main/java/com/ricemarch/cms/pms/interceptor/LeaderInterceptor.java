package com.ricemarch.cms.pms.interceptor;

import com.ricemarch.cms.pms.aspect.UserContextHolder;
import com.ricemarch.cms.pms.dto.CustomUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author RiceMarch
 * @since 2021/4/6 23:13
 */
@Component
@Slf4j
public class LeaderInterceptor implements HandlerInterceptor {



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        CustomUser user = UserContextHolder.getUser();

        return false;
    }
}
