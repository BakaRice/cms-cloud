package com.ricemarch.cms.pms.bo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * @author RiceMarch
 * @since 2021/4/8 21:17
 */
@Data
@Slf4j
public class InstitutionCommonRequest {

    @ApiModelProperty("机构Id")
    private Long id;

    @ApiModelProperty("机构名称")
    private String name;

    private Long companyId;

    @ApiModelProperty("机构地址")
    private String address;

    @ApiModelProperty("机构描述")
    private String description;

    @ApiModelProperty("机构联系人姓名")
    private String contactName;

    @ApiModelProperty("机构联系人手机")
    private String contactMobilePhone;

    private Long createBy;

    private LocalDateTime createTime;

    private Long updateBy;

    private LocalDateTime updateTime;
}
