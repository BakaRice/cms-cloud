package com.ricemarch.cms.pms.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

/**
 * 用于显示七日数据的
 *
 * @author RiceMarch
 * @date 2021/5/28 16:51
 */
@Data
@Slf4j
@Accessors(chain = true)
public class AttendanceOverviewDto {
    private List<AttendancesOverview> sevenList;
}
