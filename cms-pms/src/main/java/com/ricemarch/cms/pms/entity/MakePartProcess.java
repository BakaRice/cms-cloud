package com.ricemarch.cms.pms.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 零件加工记录表
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MakePartProcess implements Serializable {

    private static final long serialVersionUID = 1L;

      private Long id;

    /**
     * 零件加工编码
     */
    private String workId;

    /**
     * 零件加工状态 0 毛胚，1加工中，2成品，3废品
     */
    private Integer status;

    /**
     * 零件标识 => CARGO_CODE (SP20210521A0001)
     */
    private String codeId;

    /**
     * 毛胚出库时间
     */
    private LocalDateTime startTime;

    /**
     * 流程结束时间 废品了
     */
    private LocalDateTime endTime;

    /**
     * 完工时间
     */
    private LocalDateTime finishTime;

    /**
     * 零件流转状态编码 1111 0000 ... 0001
     */
    private String flowCode;

    /**
     * 零件处理比标志 1/0 
     */
    private Integer done;

    /**
     * 零件测量记录id
     */
    private Long testId;


}
