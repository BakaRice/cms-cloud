package com.ricemarch.cms.pms.mapper;

import com.ricemarch.cms.pms.entity.MakePartProcess;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 零件加工记录表 Mapper 接口
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-21
 */
@Repository
public interface MakePartProcessMapper extends BaseMapper<MakePartProcess> {

    MakePartProcess findByCode(@Param("code") String code);
}
