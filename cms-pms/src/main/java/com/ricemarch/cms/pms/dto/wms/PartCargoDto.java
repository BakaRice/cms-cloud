package com.ricemarch.cms.pms.dto.wms;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author RiceMarch
 * @date 2021/5/21 8:39
 *  {
 *         id: "",
 *         name: "零件名称0001",
 *         code: "PS20210521A0001",
 *         supplierName: "供应商名称1",
 *         supplierId: "1",
 *         cargoTypeId: "0",
 *         cargoTypeName: "零件",
 *       },
 */
@Data
@Slf4j
@Accessors(chain = true)
public class PartCargoDto {
    private String name;
//    private String code;
    private String supplierName;
    private Long supplierId;
    private String cargoTypeId;
    private String cargoTypeName;
}
