package com.ricemarch.cms.pms.dto.wms;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * @author RiceMarch
 * @date 2021/5/21 9:13
 */
@Data
@Slf4j
@Accessors(chain = true)
public class PartCargoDetailDto {
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

    private String supplierName;
}
