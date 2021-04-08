package com.ricemarch.cms.pms.bo.request.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author RiceMarch
 * @since 2021/4/5 17:54
 */
@Data
@Accessors(chain = true)
@Slf4j
public class AdminRoleReq implements Serializable {

    @ApiModelProperty("角色名")
    @NotNull(message = "角色名不能为空")
    private String name;

    private Long createBy;

    private LocalDateTime createTime;

    private Long updateBy;

    private LocalDateTime updateTime;
}
