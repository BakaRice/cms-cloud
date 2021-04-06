package com.ricemarch.cms.pms.bo.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author RiceMarch
 * @date 2021/3/24 22:34
 */
@Data
@Slf4j
public class CmsBaseRequest implements Serializable {
    @ApiModelProperty("班组id")
    @JsonIgnore()
    private Long cellId;

    @ApiModelProperty("机构id")
    @JsonIgnore()
    private Long institutionId;

    @ApiModelProperty("公司id")
    @JsonIgnore()
    private Long companyId;

    @ApiModelProperty("用户id")
    @JsonIgnore()
    private String userId;

    @ApiModelProperty("用户名称")
    @JsonIgnore()
    private String userName;
}
