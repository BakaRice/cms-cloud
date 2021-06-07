package com.ricemarch.cms.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ricemarch.cms.pms.entity.Warehouse;
import com.ricemarch.cms.pms.mapper.WarehouseMapper;
import com.ricemarch.cms.pms.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 仓库 服务实现类
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-20
 */
@Service
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, Warehouse> implements WarehouseService {

    @Autowired
    WarehouseMapper warehouseMapper;

    @Override
    public List<Warehouse> getAll() {
     return    warehouseMapper.getAll();
    }
}
