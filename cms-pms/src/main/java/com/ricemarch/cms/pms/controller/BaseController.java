package com.ricemarch.cms.pms.controller;

import com.ricemarch.cms.pms.aspect.UserContextHolder;
import com.ricemarch.cms.pms.dto.BaseUser;
import com.ricemarch.cms.pms.dto.CustomUser;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * 基础controller 用于获取登录用户的基础信息
 *
 * @author tanwentao
 * @since 2021/4/6
 */
@Slf4j
public class BaseController {

    protected CustomUser getCustomer() {
        return UserContextHolder.getUser();
    }

    protected static String getUserName() {
        return Optional.ofNullable(UserContextHolder.getUserName()).orElse("");
    }

    protected static Long getUserId() {
        return Optional.ofNullable(UserContextHolder.getUserId()).orElse(null);
    }

    //CellId
    protected static Long getCellId() {
        return UserContextHolder.getCellId();
    }

    //CompanyId
    protected static Long getCompanyId() {
        return UserContextHolder.getCompanyId();
    }

    //institutionid
    protected static Long getInstitutionId() {
        return UserContextHolder.getInstitutionId();
    }

    //profressionid
    protected static Integer getProfessionId() {
        return UserContextHolder.getProfessionId();
    }

    //roleid
    protected static Integer getRoleId() {
        return UserContextHolder.getRoleId();
    }
}
