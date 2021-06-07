package com.ricemarch.cms.pms.service.impl;

import com.google.common.collect.Lists;
import com.ricemarch.cms.pms.common.expection.PmsServiceException;
import com.ricemarch.cms.pms.dto.DateSchedulesDto;
import com.ricemarch.cms.pms.dto.DateSchedulesLinkDto;
import com.ricemarch.cms.pms.dto.DateSchedulesListDto;
import com.ricemarch.cms.pms.dto.Roster;
import com.ricemarch.cms.pms.entity.Scheduling;
import com.ricemarch.cms.pms.mapper.SchedulingMapper;
import com.ricemarch.cms.pms.service.SchedulingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public Map<Long, List<Roster>> getUserExistRosterListByUserIdList(List<Long> userIdList) {
        Map<Long, List<Roster>> map = new HashMap<>();
        for (Long aLong : userIdList) {
            List<Roster> schedulings = schedulingMapper.selectRosterList(aLong);
            map.put(aLong, schedulings);
        }
        return map;
    }

    @Override
    public Scheduling getRosterByUserId(Long uid) {
        return schedulingMapper.getRosterByUserId(uid);
    }

    @Override
    public DateSchedulesListDto get60Schedules(LocalDate date, Long userId, Integer offset) {
        DateSchedulesListDto dateSchedulesListDto = new DateSchedulesListDto();
        List<DateSchedulesLinkDto> linkDtoList = new ArrayList<>();
        if (date == null) {
            date = LocalDate.now();
        }
        LocalDate startTime = date.plusDays(-offset);
        LocalDate endTime = date.plusDays(offset);
        List<Scheduling> list = schedulingMapper.get60Schedules(startTime, endTime, userId);
        List<DateSchedulesDto> r = new ArrayList<>();

        int pointIndex = 1;
        for (Scheduling scheduling : list) {
            LocalDate startTime1 = scheduling.getStartTime();
            LocalDate endTime1 = scheduling.getEndTime();
            LocalDate point = LocalDate.from(startTime1);
            while (!point.isAfter(endTime1)) {
                DateSchedulesDto dto = new DateSchedulesDto();
                dto.setUserId(userId).setSTypeId(scheduling.getScheduleTypeId()).setCurrDate(point);
                if (point.isEqual(startTime1) || point.isEqual(endTime1)) {
                    dto.setPoint(true);
                    dto.setPointIndex(pointIndex);
                } else {
                    dto.setPoint(false);
                }
                r.add(dto);
                point = point.plusDays(1);

            }
            pointIndex++;
        }
        Collections.sort(r, Comparator.comparing(DateSchedulesDto::getCurrDate));

        List<DateSchedulesDto> dateSchedulesDtoList = r;
        dateSchedulesListDto.setSchedulesDtoList(dateSchedulesDtoList).setStartTime(date.plusDays(-offset)).setEndTime(date.plusDays(offset));
        int size = 0;
        if ((size = dateSchedulesDtoList.size()) > 0) {
            LocalDate stmp = dateSchedulesDtoList.get(0).getCurrDate();
            LocalDate etmp = dateSchedulesDtoList.get(size - 1).getCurrDate();
            if (stmp.isBefore(date.plusDays(-offset))) {
                dateSchedulesListDto.setStartTime(stmp);
            }
            if (etmp.isAfter(date.plusDays(offset))) {
                dateSchedulesListDto.setEndTime(etmp);
            }
        }
        List<DateSchedulesDto> rp = r.stream().filter(e -> e.getPoint()).collect(Collectors.toList());
        int linkIndex = 0;
        int currPointIndex = 0;
        for (int i = 0; i < rp.size(); i++) {
            if (rp.get(i).getPoint()) {
                currPointIndex = rp.get(i).getPointIndex();
                for (int i1 = i + 1; i1 < rp.size(); i1++) {
                    if (rp.get(i1).getPoint() && rp.get(i1).getPointIndex() == currPointIndex) {
                        DateSchedulesLinkDto linkDto = new DateSchedulesLinkDto();
                        linkDto.setSource(i);
                        linkDto.setTarget(i1);
                        linkDtoList.add(linkDto);
                    }
                }
            }
        }
        dateSchedulesListDto.setLink(linkDtoList);
        return dateSchedulesListDto;
    }
}
