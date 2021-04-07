package com.ricemarch.cms.pms.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Blob;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class Scheduling implements Serializable {

    private static final long serialVersionUID = 1L;

      private String id;

    private String userId;

    /**
     * 排班类型
     */
    private Integer scheduleTypeId;

    /**
     * 本期排班开始时间
     */
    private LocalDate startTime;

    /**
     * 本期排班结束时间
     */
    private LocalDate endTime;

    private Long createBy;

    private LocalDateTime createTime;

    private Long updateBy;

    private LocalDateTime updateTime;

    private Blob isDelete;


}
