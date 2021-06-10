package com.ricemarch.cms.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ricemarch.cms.pms.entity.WarehouseInboundDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-20
 */
@Repository
public interface WarehouseInboundDetailMapper extends BaseMapper<WarehouseInboundDetail> {

    List<WarehouseInboundDetail> getByInboundId(int inboundId);
}
