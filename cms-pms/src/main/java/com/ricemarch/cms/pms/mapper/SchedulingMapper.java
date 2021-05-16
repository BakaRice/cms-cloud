package com.ricemarch.cms.pms.mapper;

import com.ricemarch.cms.pms.dto.Roster;
import com.ricemarch.cms.pms.entity.Scheduling;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ricemarch
 * @since 2021-03-22
 */
@Repository
public interface SchedulingMapper extends BaseMapper<Scheduling> {
    List<Scheduling> selectListByTimeAndUserIdList(LocalDate startTime, LocalDate endTime, List<Long> userIdList);

    List<Roster> selectRosterList(@Param("aLong")Long aLong);
}
