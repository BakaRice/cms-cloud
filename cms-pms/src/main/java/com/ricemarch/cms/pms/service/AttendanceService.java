package com.ricemarch.cms.pms.service;

import com.ricemarch.cms.pms.entity.Attendance;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDate;

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
}
