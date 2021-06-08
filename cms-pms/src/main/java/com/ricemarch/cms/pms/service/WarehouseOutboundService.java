package com.ricemarch.cms.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ricemarch.cms.pms.entity.WarehouseOutbound;

import java.util.List;

/**
 * <p>
 * 仓库出库单 服务类
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-20
 */
public interface WarehouseOutboundService extends IService<WarehouseOutbound> {

    int outByIdList(List<String> cargoCodeList, Long oid, String userName, Long uid);

    int outByIdList2(List<String> cargoCodeList, Long id, String userName, Long userId);
}
