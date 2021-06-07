package com.ricemarch.cms.pms.mapper;

import com.ricemarch.cms.pms.dto.wms.CargoNumDto;
import com.ricemarch.cms.pms.dto.wms.SunburstItem;
import com.ricemarch.cms.pms.dto.wms.SupplierOverviewDto;
import com.ricemarch.cms.pms.entity.WarehouseSupplier;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    List<SupplierOverviewDto> getAllList();

    List< CargoNumDto> getPartTotalTypeNumList(List<Long> supplierIdList);

    List<CargoNumDto> getSpacePartTotalTypeNumList(List<Long> supplierIdList);

    List< CargoNumDto> getPartTotalNumList(List<Long> supplierIdList);

    List< CargoNumDto> getSpacePartTotalNumList(List<Long> supplierIdList);

    List<WarehouseSupplier> getAll();
}
