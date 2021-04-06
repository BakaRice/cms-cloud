package com.ricemarch.cms.pms.entity;

import java.time.LocalDateTime;
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
public class Attendance implements Serializable {

    private static final long serialVersionUID = 1L;

      private String id;

    private String userId;

    private Integer scheduleTypeId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    /**
     * 考勤状态(0正常，1迟到，2早退，3旷工）
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;


}
