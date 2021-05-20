package com.ricemarch.cms.pms.service.impl;

import com.ricemarch.cms.pms.dto.AttendanceDto;
import com.ricemarch.cms.pms.dto.AttendancesOverview;
import com.ricemarch.cms.pms.entity.Attendance;
import com.ricemarch.cms.pms.mapper.AttendanceMapper;
import com.ricemarch.cms.pms.service.AttendanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ricemarch
 * @since 2021-03-22
 */
@Service
public class AttendanceServiceImpl extends ServiceImpl<AttendanceMapper, Attendance> implements AttendanceService {

    @Autowired
    AttendanceMapper attendanceMapper;

    @Override
    public Attendance getByCurrDateAndUid(LocalDate currDate, Long userId) {
        return attendanceMapper.getByCurrDateAndUid(currDate, userId);
    }

    @Override
    public List<AttendanceDto> selectDtoListByCellAndInitId(Long institutionId, Long cellId, LocalDate date) {
        return attendanceMapper.selectDtoListByCellAndInitId(institutionId, cellId, date);
    }

    @Override
    public AttendancesOverview getOverviewByDate(LocalDate date, Long institutionId, Long cellId) {
        AttendancesOverview attendancesOverview = new AttendancesOverview();
//-- /        考勤状态(0正常，1迟到，2早退，3旷工，4请假，5出差）
        int c = attendanceMapper.getOverviewCount(0, date);
        attendancesOverview.setSuccess(c);
        c = attendanceMapper.getOverviewCount(1, date);
        attendancesOverview.setLate(c);

        c = attendanceMapper.getOverviewCount(2, date);
        attendancesOverview.setEarly(c);

        c = attendanceMapper.getOverviewCount(3, date);
        attendancesOverview.setUndeal(c);

        c = attendanceMapper.getOverviewCount(4, date);
        attendancesOverview.setOff(c);

        c = attendanceMapper.getOverviewCount(5, date);
        attendancesOverview.setTrip(c);

        c = attendanceMapper.getAllOverview(date, cellId, institutionId);
        attendancesOverview.setAll(c);

        attendancesOverview.setUndeal(attendancesOverview.getAll()
                - attendancesOverview.getSuccess()
                - attendancesOverview.getEarly()
                - attendancesOverview.getLate()
                - attendancesOverview.getOff()
                - attendancesOverview.getTrip());

        return attendancesOverview;


    }
}
