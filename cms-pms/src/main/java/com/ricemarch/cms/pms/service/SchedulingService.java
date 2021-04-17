package com.ricemarch.cms.pms.service;

import com.ricemarch.cms.pms.entity.Scheduling;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ricemarch.cms.pms.entity.User;

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
public interface SchedulingService extends IService<Scheduling> {

    List<Scheduling> getUserListByTimeAndUserIdList(LocalDate startTime, LocalDate endTime, List<Long> userIdList);
}
