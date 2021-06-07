package com.ricemarch.cms.pms.dto;

import com.ricemarch.cms.pms.entity.Attendance;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;

/**
 * @author RiceMarch
 * @date 2021/5/27 18:21
 */
@Data
@Slf4j
@Accessors(chain = true)
public class ClockInDto {

    private Long uid;
    private String userName;
    private String statusDesc;
    private Integer statusCode;
    private String schedulingTypeDesc;
    private LocalTime startTime;
    private LocalTime endTime;
    private Attendance attendance;

}
