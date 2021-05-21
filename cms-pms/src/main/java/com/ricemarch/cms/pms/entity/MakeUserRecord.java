package com.ricemarch.cms.pms.entity;

import java.time.LocalDateTime;
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
public class MakeUserRecord implements Serializable {

    private static final long serialVersionUID = 1L;

      private Long id;

    private Long userId;

    private String partCode;

    private LocalDateTime time;

    private Long seqId;


}
