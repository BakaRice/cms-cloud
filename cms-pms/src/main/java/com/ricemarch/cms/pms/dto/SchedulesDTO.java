package com.ricemarch.cms.pms.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.sql.Blob;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 排班 类
 *
 * @author RiceMarch
 * @since 2021/4/17 11:48
 */
@Data
@Slf4j
public class SchedulesDTO {
    @ApiModelProperty("排班表id")
    private Long id;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("排版类型")
    private Integer scheduleTypeId;

    @ApiModelProperty("本期排班开始时间")
    private LocalDate startTime;

    @ApiModelProperty("本期排班结束时间")
    private LocalDate endTime;

    @ApiModelProperty("创建人")
    private Long createBy;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("修改人")
    private Long updateBy;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("是否删除")
    private Blob isDelete;
}
