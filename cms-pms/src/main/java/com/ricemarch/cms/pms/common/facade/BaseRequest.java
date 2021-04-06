package com.ricemarch.cms.pms.common.facade;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author RiceMarch
 * @date 2021/3/7 16:23
 */
@Setter
@Getter
@ToString(callSuper = true)
public class BaseRequest<T> {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "postData不能为空")
    @ApiModelProperty(value = "请求对象内容")
    @Valid
    public T postData;
}
