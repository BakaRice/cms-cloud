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
 * @since 2021-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Institution implements Serializable {

    private static final long serialVersionUID = 1L;

      private Long id;

    private String name;

    private String address;

    /**
     * 公司描述
     */
    private String description;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系人手机
     */
    private String contactMobilePhone;

    private Long createBy;

    private LocalDateTime createTime;

    private Long updateBy;

    private LocalDateTime updateTime;


}
