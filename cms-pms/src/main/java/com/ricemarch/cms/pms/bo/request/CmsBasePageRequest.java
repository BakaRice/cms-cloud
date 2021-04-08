package com.ricemarch.cms.pms.bo.request;

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
    private int pageNum = 0;
    private int pageSize = 10;
}
