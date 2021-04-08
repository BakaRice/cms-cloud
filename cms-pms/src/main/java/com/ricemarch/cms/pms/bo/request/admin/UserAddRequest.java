package com.ricemarch.cms.pms.bo.request.admin;

import com.ricemarch.cms.pms.bo.request.CmsBaseRequest;
import com.ricemarch.cms.pms.bo.request.UserCommonRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;

/**
 * @author RiceMarch
 * @date 2021/3/24 22:46
 */
@Data
@Slf4j
public class UserAddRequest extends CmsBaseRequest {

    @NotNull(message = "用户信息不能为空")
    @ApiModelProperty("用户信息")
    UserCommonRequest userCommonRequest;

}