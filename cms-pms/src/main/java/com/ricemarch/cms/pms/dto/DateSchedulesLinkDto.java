package com.ricemarch.cms.pms.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author RiceMarch
 * @date 2021/6/3 20:41
 */
@Data
@Accessors(chain = true)
public class DateSchedulesLinkDto {
    private Integer source;
    private Integer target;


}
