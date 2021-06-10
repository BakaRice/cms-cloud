package com.ricemarch.cms.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ricemarch.cms.pms.dto.wms.InboundDto;
import com.ricemarch.cms.pms.entity.WarehouseInboundDetail;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-20
 */
public interface WarehouseInboundDetailService extends IService<WarehouseInboundDetail> {

    InboundDto getInboundDto(int inboundId);
}
