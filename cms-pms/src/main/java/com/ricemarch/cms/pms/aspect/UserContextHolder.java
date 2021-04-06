package com.ricemarch.cms.pms.aspect;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.ricemarch.cms.pms.dto.BaseUser;
import com.ricemarch.cms.pms.dto.CustomUser;

import java.util.Optional;

/**
 * 当前登录用户持有者
 *
 * @author tanwentao
 * @since 2021/3/8
 */

public class UserContextHolder {
    public static final ThreadLocal<CustomUser> USER_LOCAL = new TransmittableThreadLocal<>();

    public static CustomUser getUser() {
        return USER_LOCAL.get();
    }

    /**
     * 设置当前登录用户信息
     */
    public static void setUser(CustomUser user) {
        USER_LOCAL.set(user);
    }

    public static String getUserName() {
        return Optional.ofNullable(getUser()).map(BaseUser::getName)
                .orElse(null);
    }

    public static Long getUserId() {
        return Optional.ofNullable(getUser()).map(BaseUser::getId)
                .orElse(null);
    }

    //CellId
    public static Long getCellId() {
        return Optional.ofNullable(getUser()).map(BaseUser::getCellId)
                .orElse(null);
    }

    //CompanyId
    public static Long getCompanyId() {
        return Optional.ofNullable(getUser()).map(BaseUser::getCompanyId)
                .orElse(null);
    }

    //institutionid
    public static Long getInstitutionId() {
        return Optional.ofNullable(getUser()).map(BaseUser::getInstitutionId)
                .orElse(null);
    }

    //profressionid
    public static Integer getProfessionId() {
        return Optional.ofNullable(getUser()).map(BaseUser::getProfessionId)
                .orElse(null);
    }

    //roleid
    public static Integer getRoleId() {
        return Optional.ofNullable(getUser()).map(BaseUser::getRoleId)
                .orElse(null);
    }

    public static void remove() {
        USER_LOCAL.remove();
    }

}
