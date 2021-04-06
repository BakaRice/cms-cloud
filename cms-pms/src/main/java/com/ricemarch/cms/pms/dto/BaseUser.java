package com.ricemarch.cms.pms.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 用户 数据传输对象
 *
 * @author RiceMarch
 * @since 2021/3/7 15:44
 */
@Data
@Slf4j
public class BaseUser {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long companyId;

    private Long institutionId;

    private Long cellId;

    private Integer roleId;

    private Integer professionId;

    private String name;

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
