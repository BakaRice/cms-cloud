package com.ricemarch.cms.pms.mapper;

import com.ricemarch.cms.pms.dto.wms.SunburstItem;
import com.ricemarch.cms.pms.entity.WarehouseSupplier;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 供应商 Mapper 接口
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-20
 */
@Repository
public interface WarehouseSupplierMapper extends BaseMapper<WarehouseSupplier> {

    List<WarehouseSupplier> getSupplierList();
}
