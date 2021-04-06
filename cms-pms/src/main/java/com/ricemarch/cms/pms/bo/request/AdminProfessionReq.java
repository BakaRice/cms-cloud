package com.ricemarch.cms.pms.bo.request;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author RiceMarch
 * @since 2021/4/5 18:33
 */
@Data
@Accessors(chain = true)
@Slf4j
public class AdminProfessionReq {

    @ApiModelProperty("工种编号")
    @NotNull(message = "工种编号不能为空")
    private String code;

    //（生产，保全，检查，仓储，文职，...)
    @ApiModelProperty("工种名称")
    @NotNull(message = "工种名称不能为空")
    private String name;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
