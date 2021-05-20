package com.ricemarch.cms.pms.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 仓库出库单
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WarehouseOutbound implements Serializable {

    private static final long serialVersionUID = 1L;

      private Long id;

    private Long warehouseId;

    private Integer count;

    private Long createBy;

    private String createName;

    private LocalDateTime createTime;


}
