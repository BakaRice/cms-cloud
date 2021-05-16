package com.ricemarch.cms.pms.mapper;

import com.ricemarch.cms.pms.dto.AttendanceDto;
import com.ricemarch.cms.pms.entity.Attendance;
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
public interface AttendanceMapper extends BaseMapper<Attendance> {

    Attendance getByCurrDateAndUid(@Param("currDate")LocalDate currDate,@Param("userId") Long userId);

    List<AttendanceDto> selectDtoListByCellAndInitId(@Param("institutionId") Long institutionId, @Param("cellId") Long cellId,@Param("date") LocalDate date);
}
