package com.ricemarch.cms.pms.interceptor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ricemarch.cms.pms.aspect.UserContextHolder;
import com.ricemarch.cms.pms.common.component.EncryptComponent;
import com.ricemarch.cms.pms.common.component.MyToken;
import com.ricemarch.cms.pms.dto.CustomUser;
import com.ricemarch.cms.pms.entity.User;
import com.ricemarch.cms.pms.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * @author RiceMarch
 * @date 2021/3/7 17:06
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private EncryptComponent encryptComponent;

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        MyToken myToken = Optional.ofNullable(request.getHeader(MyToken.AUTHORIZATION))
                .map(auth -> encryptComponent.decryptToken(auth))
                .orElseThrow((() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "未登录")));
        String phone = Optional.of(myToken.getPhone())
                .orElseThrow((() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "登录失败")));
        String role = Optional.of(myToken.getRole())
                .orElseThrow((() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "登录失败")));
        request.setAttribute(MyToken.PHONE, phone);
        request.setAttribute(MyToken.ROLE, role);
        //async execute 好像不能用多綫程 會存在問題
        this.setUser(phone);

        return true;
    }

    //    @Async("asyncServiceExecutor")

    /**
     * 通過phone查詢User 並存入UserContextHolder中
     */
    public void setUser(String phone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        //通過phone可以獲取到user時 存儲UserContextHolder
        Optional.ofNullable(userService.getOne(queryWrapper))
                .ifPresent(user -> {
                    CustomUser customUser = new CustomUser();
                    BeanUtils.copyProperties(user, customUser);
                    log.debug("{}", customUser.toString());
                    UserContextHolder.setUser(customUser);
                });

    }

}
