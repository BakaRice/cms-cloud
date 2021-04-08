package com.ricemarch.cms.pms.bo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;

/**
 * cell 添加实体类
 *
 * @author RiceMarch
 * @since 2021/4/8 21:14
 */
@Data
@Slf4j
public class CellAddRequest {

    @NotNull(message = "班组信息不能为空")
    @ApiModelProperty("班组信息")
    CellCommonRequest cellCommonRequest;
}
