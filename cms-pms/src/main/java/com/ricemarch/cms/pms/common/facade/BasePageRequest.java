package com.ricemarch.cms.pms.common.facade;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author RiceMarch
 * @date 2021/3/7 16:22
 */
@Setter
@Getter
@ToString(callSuper = true)
public class BasePageRequest<T> extends BaseRequest<T> {


    private static final long serialVersionUID = 1L;

    /**
     * 开始条数（0开始）
     */
    @ApiModelProperty(value = "开始条数（0开始）")
    private Integer offSet;

    /**
     * 本次查询条数
     */
    @ApiModelProperty(value = "本次查询条数")
    private Integer length;
}

