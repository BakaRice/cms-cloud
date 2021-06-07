package com.ricemarch.cms.pms.controller;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 工序信息实体类
 *
 * @author RiceMarch
 * @date 2021/6/7 10:27
 */
@Data
@Accessors(chain = true)
public class PartSeqDto {
    private String name;
    private String code;

}
