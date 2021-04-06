package com.ricemarch.cms.pms.bo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;

/**
 * @author RiceMarch
 * @date 2021/3/25 23:01
 */

@Data
@Slf4j
public class UserUpdateRequest extends CmsBaseRequest {

    @NotNull(message = "用户信息不能为空")
    @ApiModelProperty("用户信息")
    UserCommonRequest userCommonRequest;

}