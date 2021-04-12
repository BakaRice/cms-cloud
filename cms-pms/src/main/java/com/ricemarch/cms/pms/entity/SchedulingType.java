package com.ricemarch.cms.pms.entity;

import java.time.LocalTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author ricemarch
 * @since 2021-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SchedulingType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    @ApiModelProperty(value = "08:00:00")
    private LocalTime startTime;

    @ApiModelProperty(value = "17:00:00")
    private LocalTime endTime;


}
