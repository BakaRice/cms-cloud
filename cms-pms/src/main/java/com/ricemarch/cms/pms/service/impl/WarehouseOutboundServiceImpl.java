package com.ricemarch.cms.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ricemarch.cms.pms.entity.WarehouseOutbound;
import com.ricemarch.cms.pms.mapper.WarehouseOutboundMapper;
import com.ricemarch.cms.pms.service.WarehouseOutboundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 仓库出库单 服务实现类
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-20
 */
@Service
public class WarehouseOutboundServiceImpl extends ServiceImpl<WarehouseOutboundMapper, WarehouseOutbound> implements WarehouseOutboundService {

    @Autowired
    WarehouseOutboundMapper warehouseOutboundMapper;

    @Override
    public int outByIdList(List<String> cargoCodeList, Long oid, String userName, Long uid) {
        return warehouseOutboundMapper.outByIdList(cargoCodeList,oid,userName,uid);
    }

    @Override
    public int outByIdList2(List<String> cargoCodeList, Long oid, String userName, Long uid) {
        return warehouseOutboundMapper.outByIdList2(cargoCodeList,oid,userName,uid);
    }
}
