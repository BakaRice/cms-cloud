package com.ricemarch.cms.pms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 标准作业书流程
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MakeWorkBookProcess implements Serializable {

    private static final long serialVersionUID = 1L;

      private Long id;

    private Long workBookId;

    private Integer processNo;

    private String explanation;

    private String minutes;

    private String seconds;

    @TableField("MainSteps")
    private String mainsteps;

    private String focus;


}
