package com.ricemarch.cms.pms.mapper;

import com.ricemarch.cms.pms.dto.Roster;
import com.ricemarch.cms.pms.dto.RosterOverview;
import com.ricemarch.cms.pms.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 员工 Mapper 接口
 * </p>
 *
 * @author ricemarch
 * @since 2021-03-22
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过userIdList查询userList
     *
     * @param userIdList
     * @return
     */
    List<User> selectIdList(@Param("userIdList") List<Long> userIdList);

    List<Roster> selectRoster(@Param("cellId") Long cellId, @Param("InitId") Long InitId);

    Integer selectRosterUserCount(Long cellId, Long InitId);

    Integer selectRosterUnDealUserCount(Long cellId, Long InitId);

    Integer selectRosterDayUserCount(Long cellId, Long InitId);

    Integer selectRosterNightUserCount(Long cellId, Long InitId);
}
