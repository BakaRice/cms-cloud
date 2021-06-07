package com.ricemarch.cms.pms.dto.wms;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author RiceMarch
 * @date 2021/6/4 17:15
 */
@Data
@Accessors(chain = true)
public class CargoNumDto {
    private Long supplierId;
    private Long num;
}
