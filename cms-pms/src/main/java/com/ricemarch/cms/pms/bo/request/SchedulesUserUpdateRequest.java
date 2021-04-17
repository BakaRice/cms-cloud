package com.ricemarch.cms.pms.bo.request;

import com.ricemarch.cms.pms.dto.SchedulesDTO;
import com.ricemarch.cms.pms.dto.SchedulingTypeDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * 用于修改用户排班信息的请求类
 *
 * @author RiceMarch
 * @since 2021/4/17 11:53
 */
@Data
@Slf4j
public class SchedulesUserUpdateRequest {

    @ApiModelProperty("排班类型id")
    @NotNull(message = "排班类型不能为空")
    @NonNull
    Integer schedulingTypeId;

    @ApiModelProperty("本期排班开始时间")
    @NotNull(message = "本期排班开始时间不能为空")
    @NonNull
    private LocalDate startTime;

    @ApiModelProperty("本期排班结束时间")
    @NotNull(message = "本期排班结束时间不能为空")
    @NonNull
    private LocalDate endTime;

    @ApiModelProperty("用户列表")
    @NotNull(message = "用户列表不能为空")
    @NonNull
    List<UserCommonRequest> userCommonRequestList;
}
