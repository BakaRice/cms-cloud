package com.ricemarch.cms.pms.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 零件质量级别和要求设定
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QualityPartClaim implements Serializable {

    private static final long serialVersionUID = 1L;

      private Long id;

    /**
     * 零件名称
     */
    private String partName;

    /**
     * 级别类型 1111 1110 1100
     */
    private String level;


}
