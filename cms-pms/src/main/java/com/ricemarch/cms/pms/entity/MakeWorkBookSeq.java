package com.ricemarch.cms.pms.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 工序
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MakeWorkBookSeq implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    //直接存入的是零件的名称 因为零件名称唯一确定零件的种类
    private String code;

    private String name;

    //工序 顺序
    private Integer value;

    public MakeWorkBookSeq(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
