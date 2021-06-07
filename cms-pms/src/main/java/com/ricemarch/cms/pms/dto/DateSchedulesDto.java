package com.ricemarch.cms.pms.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

/**
 * 每日的考勤安排
 *
 * @author RiceMarch
 * @date 2021/6/3 17:53
 */
@Data
@Accessors(chain = true)
@Slf4j
public class DateSchedulesDto {

    private Boolean point;
    private String userName;
    private Long userId;
    private Integer sTypeId;
    private LocalDate currDate;
    private Integer pointIndex;


//    @Override
    public int compareTo(DateSchedulesDto o) {
        return currDate.isAfter(o.getCurrDate()) == true ? -1 : 1;

    }


}
