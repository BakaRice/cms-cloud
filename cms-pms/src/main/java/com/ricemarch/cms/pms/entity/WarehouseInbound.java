package com.ricemarch.cms.pms.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 入库单
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class WarehouseInbound implements Serializable {

    private static final long serialVersionUID = 1L;

      private Long id;

    private Long warehouseId;

    private Integer count;

    private Long createBy;

    private String createName;

    private LocalDateTime createTime;


}
