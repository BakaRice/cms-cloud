package com.ricemarch.cms.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ricemarch.cms.pms.entity.Warehouse;

import java.util.List;

/**
 * <p>
 * 仓库 服务类
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-20
 */
public interface WarehouseService extends IService<Warehouse> {

    List<Warehouse> getAll();
}
