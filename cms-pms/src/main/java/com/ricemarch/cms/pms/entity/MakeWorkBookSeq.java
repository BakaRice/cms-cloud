package com.ricemarch.cms.pms.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 工序
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MakeWorkBookSeq implements Serializable {

    private static final long serialVersionUID = 1L;

      private Long id;

    private String code;

    private String name;


}
