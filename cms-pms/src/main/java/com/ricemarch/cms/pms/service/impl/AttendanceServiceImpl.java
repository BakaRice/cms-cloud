package com.ricemarch.cms.pms.service.impl;

import java.time.LocalDateTime;

import java.time.LocalTime;

import com.ricemarch.cms.pms.common.expection.PmsServiceException;
import com.ricemarch.cms.pms.dto.AttendanceDto;
import com.ricemarch.cms.pms.dto.AttendancesOverview;
import com.ricemarch.cms.pms.dto.ClockInDto;
import com.ricemarch.cms.pms.entity.Attendance;
import com.ricemarch.cms.pms.entity.Scheduling;
import com.ricemarch.cms.pms.entity.SchedulingType;
import com.ricemarch.cms.pms.entity.User;
import com.ricemarch.cms.pms.mapper.AttendanceMapper;
import com.ricemarch.cms.pms.mapper.SchedulingTypeMapper;
import com.ricemarch.cms.pms.mapper.UserMapper;
import com.ricemarch.cms.pms.service.AttendanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ricemarch.cms.pms.service.SchedulingService;
import com.ricemarch.cms.pms.service.SchedulingTypeService;
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


    @Autowired
    SchedulingTypeMapper schedulingTypeMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    SchedulingService schedulingService;

    @Override
    public Attendance getByCurrDateAndUid(LocalDate currDate, Long userId) {
        return attendanceMapper.getByCurrDateAndUid(currDate, userId);
    }

    @Override
    public List<AttendanceDto> selectDtoListByCellAndInitId(Long institutionId, Long cellId, LocalDate date) {
        List<AttendanceDto> attendanceDtos = attendanceMapper.selectDtoListByCellAndInitId(institutionId, cellId, date);
        return attendanceDtos;
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

        attendancesOverview.setCurrDate(date);
        return attendancesOverview;


    }

    @Override
    public ClockInDto getClockInfo(Long uid, LocalDate currDate) {
        ClockInDto clockInDto = new ClockInDto();
        //组装用户信息
        User userDB = userMapper.selectById(uid);
        clockInDto.setUid(uid);
        clockInDto.setUserName(userDB.getName());

        //查询当前考勤信息
        Attendance byCurrDateAndUid = attendanceMapper.getByCurrDateAndUid(currDate, uid);


        if (byCurrDateAndUid != null) {
            //组装当前考勤信息
            String statusDesc = AttendanceStatusCodeToDesc(byCurrDateAndUid.getStatus());
            clockInDto.setStatusDesc(statusDesc);
            clockInDto.setStatusCode(byCurrDateAndUid.getStatus());
            clockInDto.setAttendance(byCurrDateAndUid);
        }
        //组装排版信息
        Scheduling schedulingDB = schedulingService.getRosterByUserId(uid);
        if (schedulingDB == null) {
            throw new PmsServiceException("当前用户不存在今日排班信息，无法进行打卡操作，请联系系统管理员。");
        }
        SchedulingType schedulingType = schedulingTypeMapper.selectById(schedulingDB.getScheduleTypeId());
        clockInDto.setSchedulingTypeDesc(schedulingType.getName());
        clockInDto.setStartTime(schedulingType.getStartTime());
        clockInDto.setEndTime(schedulingType.getEndTime());

        return clockInDto;
    }

    //        考勤状态(0正常，1迟到，2早退，3旷工，4请假，5出差）
    private String AttendanceStatusCodeToDesc(Integer status) {
        if (status == 0) {
            return "0正常";
        } else if (status == 1) {
            return "1迟到";
        } else if (status == 2) {
            return "2早退";
        } else if (status == 3) {
            return "3旷工";
        } else if (status == 4) {
            return "4请假";
        } else if (status == 5) {
            return "5出差";
        }
        throw new PmsServiceException("获取考勤状态描述异常!");
    }


}
