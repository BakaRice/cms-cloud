package com.ricemarch.cms.pms.bo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * @author RiceMarch
 * @since 2021/4/8 21:14
 */
@Data
@Slf4j
public class CellCommonRequest {

    @ApiModelProperty("班组Id")
    private Long id;

    private Long InstitutionId;

    @ApiModelProperty("班组名称")
    private String name;

    @ApiModelProperty("班组地址")
    private String address;

    @ApiModelProperty("班组描述")
    private String description;

    @ApiModelProperty("班组联系人姓名")
    private String contactName;

    @ApiModelProperty("班组联系人手机号")
    private String contactMobilePhone;

    private Long createBy;

    private LocalDateTime createTime;

    private Long updateBy;

    private LocalDateTime updateTime;
}
