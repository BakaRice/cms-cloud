package com.ricemarch.cms.pms.service;

import com.github.pagehelper.PageInfo;
import com.ricemarch.cms.pms.dto.wms.SupplierOverviewDto;
import com.ricemarch.cms.pms.entity.WarehouseSupplier;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 供应商 服务类
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-20
 */
public interface WarehouseSupplierService extends IService<WarehouseSupplier> {

    PageInfo<SupplierOverviewDto> getSupplierOverviewDto(int pageNum, int pageSize);

    List<WarehouseSupplier> getAll();
}
