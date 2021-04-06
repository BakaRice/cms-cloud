package com.ricemarch.cms.pms.entity;

import java.time.LocalTime;
import java.io.Serializable;
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

      private Integer id;

    private String name;

    private LocalTime startTime;

    private LocalTime endTime;


}
