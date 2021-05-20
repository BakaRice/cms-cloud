package com.ricemarch.cms.pms.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 供应商
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WarehouseSupplier implements Serializable {

    private static final long serialVersionUID = 1L;

      private Long id;

    private String supplierName;

    /**
     * 供应商联系人
     */
    private String supplierContact;

    private String supplierPhone;

    /**
     * 0 综合，1 零件，2 备件，3 其他
     */
    private Integer supplierType;

    /**
     * 0 启用，1 维护（不供货了，还管现有的），2 弃用（不供货不管了）
     */
    private Integer supplierStatus;


}
