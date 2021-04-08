package com.ricemarch.cms.pms.common.component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ricemarch.cms.pms.entity.Company;
import com.ricemarch.cms.pms.entity.Profession;
import com.ricemarch.cms.pms.entity.UserRole;
import com.ricemarch.cms.pms.service.CompanyService;
import com.ricemarch.cms.pms.service.ProfessionService;
import com.ricemarch.cms.pms.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 初始化组件 用于初始化admin用户 admin权限
 *
 * @author RiceMarch
 * @since 2021/4/6 22:43
 */
@Component
@Slf4j
public class InitComponent implements InitializingBean {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRoleService roleService;

    @Autowired
    private ProfessionService professionService;

    @Autowired
    private CompanyService companyService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void afterPropertiesSet() {
        //TODO 有问题 主键自增的问题
        //当不存在 系统管理员role（1001）和 系统管理profession（2001）时,自动进行初始化
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "系统管理员");
        UserRole isExistRole = roleService.getOne(queryWrapper);
        if (null == isExistRole) {
            UserRole userRole = new UserRole();
            userRole.setName("系统管理员");
            userRole.setId(1001);
            userRole.setCreateBy(-1L);
            userRole.setUpdateBy(-1L);
            roleService.save(userRole);
            log.debug("初始化系统管理员角色：userRole:{}", userRole);
        }
        QueryWrapper<Profession> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("code", "ADMIN2001");
        Profession isExistPro = professionService.getOne(queryWrapper1);
        if (null == isExistPro) {
            Profession profession = new Profession();
            profession.setId(2001);
            profession.setCode("ADMIN2001");
            profession.setName("系统管理");
            professionService.save(profession);
            log.debug("初始化系统管理工种：profession:{}", profession);
        }

        QueryWrapper<Company> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("name", "default");
        Company isExistCompany = companyService.getOne(queryWrapper2);
        if (null == isExistCompany) {
            Company company = new Company();
            company.setId(1000000001L);
            company.setName("default");
            company.setAddress("default address");
            company.setDescription("default description");
            company.setContactName("default contact");
            company.setContactMobilePhone("");
            companyService.save(company);
            log.debug("初始化公司，company:{}", company);
        }

    }
}
