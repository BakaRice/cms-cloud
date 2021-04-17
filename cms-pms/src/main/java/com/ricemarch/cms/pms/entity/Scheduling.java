package com.ricemarch.cms.pms.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Blob;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author ricemarch
 * @since 2021-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Scheduling implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    /**
     * 班次类型（白班，夜班等）
     */
    private Integer scheduleTypeId;

    /**
     * 本次排版开始时间 如周一 等
     */
    private LocalDate startTime;

    /**
     * 本次排版结束时间
     */
    private LocalDate endTime;

    private Long createBy;

    private LocalDateTime createTime;

    private Long updateBy;

    private LocalDateTime updateTime;

    /**
     * 是否已删除 0（默认，未删除），1（已删除）
     */
    private Integer isDelete = 0;

}
