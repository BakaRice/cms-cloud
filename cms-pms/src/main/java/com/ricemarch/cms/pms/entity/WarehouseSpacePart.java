package com.ricemarch.cms.pms.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 仓库备件记录
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WarehouseSpacePart implements Serializable {

    private static final long serialVersionUID = 1L;

      private Long id;

    /**
     * 备件名称
     */
    private String name;

    /**
     * 编号
     */
    private String code;

    /**
     * 备件类型编号
     */
    private String typeCode;

    private Long workId;

    /**
     * 供应商id
     */
    private Long supplierId;

    /**
     * 仓库id
     */
    private Long warehouseId;

    private Long status;

    /**
     * 入库时间
     */
    private LocalDateTime inTime;

    /**
     * 出库时间
     */
    private LocalDateTime outTime;

    private Integer isDelete;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Long createBy;

    private String createName;

    private Long updateBy;

    private String updateName;

    private Long inboundId;

    private Long outboundId;


}
