package com.ricemarch.cms.pms.interceptor;

import com.ricemarch.cms.pms.aspect.UserContextHolder;
import com.ricemarch.cms.pms.dto.CustomUser;
import com.ricemarch.cms.pms.entity.UserRole;
import com.ricemarch.cms.pms.mapper.UserRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author RiceMarch
 * @since 2021/4/6 23:10
 */
@Component
@Slf4j
public class AdminInterceptor implements HandlerInterceptor {

    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        CustomUser user = UserContextHolder.getUser();
        String phone = user.getPhone();
        Integer roleId = user.getRoleId();
        UserRole userRole = userRoleMapper.selectById(roleId);
        String sysAdmin = "系统管理员";
        if (sysAdmin.equals(userRole.getName())) {
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "无权限");
        }
    }
}
