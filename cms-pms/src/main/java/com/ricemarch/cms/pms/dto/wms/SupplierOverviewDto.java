package com.ricemarch.cms.pms.dto.wms;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用于返回供应商信息概况的list
 *
 * @author RiceMarch
 * @date 2021/6/4 16:16
 */
@Data
@Accessors(chain = true)
public class SupplierOverviewDto {
    private String supplierName;
    private Long supplierId;
    private Long totalTypeNum;
    private Long totalNum;
}
