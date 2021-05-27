package com.ricemarch.cms.pms.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 标准作业书
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-21
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class MakeWorkBook implements Serializable {

    private static final long serialVersionUID = 1L;

      private Long id;

    /**
     * 编号
     */
    private String workNo;

    private String workName;

    private String userProtectionTools;

    private String userTools;

    private String partName;

    private String partId;

    private String workSequenceName;

    private Long workSequenceId;

    private String ban;

    private String other;


}
