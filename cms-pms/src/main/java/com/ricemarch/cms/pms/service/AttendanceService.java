package com.ricemarch.cms.pms.service;

import com.ricemarch.cms.pms.dto.AttendanceDto;
import com.ricemarch.cms.pms.dto.AttendancesOverview;
import com.ricemarch.cms.pms.dto.ClockInDto;
import com.ricemarch.cms.pms.entity.Attendance;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ricemarch
 * @since 2021-03-22
 */
public interface AttendanceService extends IService<Attendance> {

    Attendance getByCurrDateAndUid(LocalDate currDate, Long userId);

    List<AttendanceDto> selectDtoListByCellAndInitId(Long institutionId, Long cellId, LocalDate date);

    AttendancesOverview getOverviewByDate(LocalDate date,Long institutionId, Long cellId);

    ClockInDto getClockInfo(Long uid, LocalDate currDate);
}
