package com.ricemarch.cms.pms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 仓库零件记录
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WarehousePart implements Serializable {

    private static final long serialVersionUID = 1L;

      private Long id;

    /**
     * 零件名称
     */
    private String name;

    /**
     * 编号
     */
    private String code;

    /**
     * 零件类型编号
     */
    @TableField("type_code")
    private Integer typeCode;

    /**
     * 零件加工编号 （毛胚没有）
     */
    private Long workId;

    /**
     * 供应商id
     */
    private Long supplierId;

    /**
     * 仓库id
     */
    private Long warehouseId;

    /**
     * 毛胚0 成品1 废品2
     */
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

    private Long inboudId;

    private Long outboudId;


}
