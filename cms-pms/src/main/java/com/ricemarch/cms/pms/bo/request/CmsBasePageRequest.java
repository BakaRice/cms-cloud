package com.ricemarch.cms.pms.bo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 基础分页请求类
 *
 * @author RiceMarch
 * @since 2021/4/5 17:21
 */
@Data
@Slf4j
public class CmsBasePageRequest {
    @ApiModelProperty("起始页数字：从1开始")
    private int pageNum = 0;
    private int pageSize = 10;
}
