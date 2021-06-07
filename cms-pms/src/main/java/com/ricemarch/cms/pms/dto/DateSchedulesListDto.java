package com.ricemarch.cms.pms.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @author RiceMarch
 * @date 2021/6/3 19:34
 */
@Data
@Accessors(chain = true)
@Slf4j
public class DateSchedulesListDto {

    private List<DateSchedulesDto> schedulesDtoList;

    private LocalDate startTime;

    private LocalDate endTime;
//    [{source: 0,target:1},{source: 1,target:2}]
    private List<DateSchedulesLinkDto> link;
}
