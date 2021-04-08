package com.ricemarch.cms.pms.bo.request.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;

/**
 * @author RiceMarch
 * @since 2021/4/8 21:17
 */
@Data
@Slf4j
public class InstitutionAddRequest {

    @NotNull(message = "机构信息不能为空")
    @ApiModelProperty("机构信息")
    InstitutionCommonRequest institutionCommonRequest;
}
