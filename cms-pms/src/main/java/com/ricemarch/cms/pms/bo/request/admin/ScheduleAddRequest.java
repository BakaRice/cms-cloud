package com.ricemarch.cms.pms.bo.request.admin;

import com.ricemarch.cms.pms.entity.Scheduling;
import com.ricemarch.cms.pms.entity.SchedulingType;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;

/**
 * @author RiceMarch
 * @since 2021/4/12 22:49
 */
@Data
@Slf4j
public class ScheduleAddRequest {

    @ApiModelProperty("排班类型")
    @NotNull(message = "排班类型不能为空")
    private SchedulingType schedulingType;
}
