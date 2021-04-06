package com.ricemarch.cms.pms.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 工种
 * </p>
 *
 * @author ricemarch
 * @since 2021-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Profession implements Serializable {

    private static final long serialVersionUID = 1L;

      private Integer id;

    /**
     * 工种编号
     */
    private String code;

    /**
     * 工种名称（生产，保全，检查，仓储，文职，...)
     */
    private String name;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
