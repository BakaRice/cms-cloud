package com.ricemarch.cms.pms.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 仓库
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;

      private Long id;

    private Integer warehouseName;

    private Integer warehouseCode;

    private Long warehouseOwner;

    private String warehouseOwnerName;

    private String warehouseOwnerPhone;

    private Integer warehouseAddress;

    /**
     * 仓库状态 0启用 1停用 ...
     */
    private Integer warehouseStatus;

    /**
     * 类型 0 综合库，1 零件库，2 备件库，3 废品库
     */
    private Integer warehouseType;


}
