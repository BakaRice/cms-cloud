package com.ricemarch.cms.pms.service.impl;

import com.google.common.collect.Lists;
import com.ricemarch.cms.pms.common.expection.PmsServiceException;
import com.ricemarch.cms.pms.entity.Scheduling;
import com.ricemarch.cms.pms.entity.User;
import com.ricemarch.cms.pms.mapper.SchedulingMapper;
import com.ricemarch.cms.pms.service.SchedulingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
public class SchedulingServiceImpl extends ServiceImpl<SchedulingMapper, Scheduling> implements SchedulingService {

    @Autowired
    SchedulingMapper schedulingMapper;

    @Override
    public List<Scheduling> getUserListByTimeAndUserIdList(LocalDate startTime, LocalDate endTime, List<Long> userIdList) {
        if (startTime == null || endTime == null || startTime.isAfter(endTime)) {
            throw new PmsServiceException("查询时间格式有误");
        }
        if (CollectionUtils.isEmpty(userIdList)) {
            return Lists.newArrayList();
        }
        return schedulingMapper.selectListByTimeAndUserIdList(startTime, endTime, userIdList);
    }
}
