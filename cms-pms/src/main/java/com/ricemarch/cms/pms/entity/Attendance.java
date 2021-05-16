package com.ricemarch.cms.pms.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

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
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Attendance implements Serializable {

    private static final long serialVersionUID = 1L;

      private Long id;

    private Long userId;

    private Integer scheduleTypeId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    /**
     * 考勤状态(0正常，1迟到，2早退，3旷工，4请假，5出差）
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    private LocalDate currDate;


}
