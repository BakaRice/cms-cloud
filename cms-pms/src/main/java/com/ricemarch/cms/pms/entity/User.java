package com.ricemarch.cms.pms.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 员工
 * </p>
 *
 * @author ricemarch
 * @since 2021-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long companyId;

    private Long institutionId;

    private Long cellId;

    private Integer roleId;

    private Integer professionId;

    private String name;

    private String password;

    /**
     * 账号状态 默认未启用
     */
    private Integer accountState;

    /**
     * 电话
     */
    private String phone;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 地址
     */
    private String address;

    /**
     * 基础薪酬
     */
    private Float salary;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 身份证
     */
    private String idcardNumber;

    /**
     * 备注
     */
    private String mark;

    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;

    private Short isDelete;


}
