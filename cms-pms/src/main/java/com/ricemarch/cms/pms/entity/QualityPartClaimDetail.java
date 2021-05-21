package com.ricemarch.cms.pms.entity;

import java.math.BigDecimal;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QualityPartClaimDetail implements Serializable {

    private static final long serialVersionUID = 1L;

      private Long id;

    private Long claimId;

    /**
     * 细节要求名称
     */
    private String detailName;

    /**
     * 最小值
     */
    private BigDecimal detailMin;

    /**
     * 最大值要求
     */
    private BigDecimal detailMax;


}
