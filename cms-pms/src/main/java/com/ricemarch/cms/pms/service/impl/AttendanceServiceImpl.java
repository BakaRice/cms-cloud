package com.ricemarch.cms.pms.service.impl;

import com.ricemarch.cms.pms.entity.Attendance;
import com.ricemarch.cms.pms.mapper.AttendanceMapper;
import com.ricemarch.cms.pms.service.AttendanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ricemarch
 * @since 2021-03-22
 */
@Service
public class AttendanceServiceImpl extends ServiceImpl<AttendanceMapper, Attendance> implements AttendanceService {

    @Override
    public Attendance getByCurrDateAndUid(LocalDate currDate, Long userId) {
        //TODO
        return null;
    }
}
