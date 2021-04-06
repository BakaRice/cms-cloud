package com.ricemarch.cms.pms.bo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;

/**
 * @author RiceMarch
 * @date 2021/3/26 23:15
 */
@Data
@Slf4j
public class UserLoginRequest {

    @ApiModelProperty("手机号")
    @NotNull(message = "手机号码不能为空")
    private String phone;

    @ApiModelProperty("密码")
    @NotNull(message = "密码不能为空")
    private String password;
}
