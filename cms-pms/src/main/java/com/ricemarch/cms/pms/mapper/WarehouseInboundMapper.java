package com.ricemarch.cms.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ricemarch.cms.pms.dto.wms.StreamDto;
import com.ricemarch.cms.pms.entity.WarehouseInbound;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 入库单 Mapper 接口
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-20
 */
@Repository
public interface WarehouseInboundMapper extends BaseMapper<WarehouseInbound> {

    List<StreamDto> getInboundList();
}
