package com.ricemarch.cms.pms.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author RiceMarch
 * @date 2021/5/25 21:03
 */
@Data
@Slf4j
@Accessors(chain = true)
public class AddUserDto {
//    @ApiModelProperty("用户id")
//    private Long id;

    @ApiModelProperty("公司id")
    private Long companyId;

    @ApiModelProperty("机构id")
    private Long institutionId;

    @ApiModelProperty("班组id")
    private Long cellId;

    @ApiModelProperty("权限id")
    private Integer roleId;

    @ApiModelProperty("工种id")
    private Integer professionId;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("账号状态 默认未启用")
    private Integer accountState;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("生日")
    private Date birthday;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("基础薪酬")
    private Float salary;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("身份证")
    private String idcardNumber;

    @ApiModelProperty("备注")
    private String mark;

//    private Long createBy;
//
//    private LocalDateTime createTime;
//
//    private Long updateBy;
//
//    private LocalDateTime updateTime;
//
//    @ApiModelProperty("是否删除")
//    private Short isDelete;
}
