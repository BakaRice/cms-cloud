package com.ricemarch.cms.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ricemarch.cms.pms.dto.wms.InboundDto;
import com.ricemarch.cms.pms.entity.WarehouseInboundDetail;
import com.ricemarch.cms.pms.mapper.WarehouseInboundDetailMapper;
import com.ricemarch.cms.pms.service.WarehouseInboundDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-20
 */
@Service
public class WarehouseInboundDetailServiceImpl extends ServiceImpl<WarehouseInboundDetailMapper, WarehouseInboundDetail> implements WarehouseInboundDetailService {

    @Autowired
    WarehouseInboundDetailMapper warehouseInboundDetailMapper;

    @Override
    public InboundDto getInboundDto(int inboundId) {
        InboundDto inboundDto = new InboundDto();
        inboundDto.setWarehouseInboundDetailList(warehouseInboundDetailMapper.getByInboundId(inboundId));
        return inboundDto;
    }
}
