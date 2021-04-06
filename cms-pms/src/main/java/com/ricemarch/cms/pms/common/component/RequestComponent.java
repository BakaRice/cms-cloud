package com.ricemarch.cms.pms.common.component;

import com.ricemarch.cms.pms.entity.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Component
@Slf4j
public class RequestComponent {
    public int getPhone() {//获取线程级的attribute
        return (int) RequestContextHolder.currentRequestAttributes()
                .getAttribute(MyToken.PHONE, RequestAttributes.SCOPE_REQUEST);
    }

    public UserRole getRole() {
        return (UserRole) RequestContextHolder.currentRequestAttributes()
                .getAttribute(MyToken.ROLE, RequestAttributes.SCOPE_REQUEST);
    }


}
