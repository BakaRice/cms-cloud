package com.ricemarch.cms.pms.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WarehouseOutboundDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long warehouseOutboundId;

    private String cargoName;

    private Long cargoSupplierId;

    /**
     * 0备件，1零部件
     */
    private Integer cargoType;

    private String cargoCode;


}
