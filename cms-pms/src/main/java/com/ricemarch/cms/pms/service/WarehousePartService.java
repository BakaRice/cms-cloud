package com.ricemarch.cms.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ricemarch.cms.pms.dto.wms.PartCargoDetailDto;
import com.ricemarch.cms.pms.dto.wms.PartCargoDto;
import com.ricemarch.cms.pms.dto.wms.SunburstItem;
import com.ricemarch.cms.pms.entity.WarehousePart;
import com.ricemarch.cms.pms.entity.WarehouseSupplier;

import java.util.List;

/**
 * <p>
 * 仓库零件记录 服务类
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-20
 */
public interface WarehousePartService extends IService<WarehousePart> {

    List<SunburstItem<WarehouseSupplier>> getSupplierList(int r, int g, int b, int r1, int g1, int b1);

    List<SunburstItem> getTypeListBySpId(Long spId,int r, int g, int b, int r1, int g1, int b1);

    List<PartCargoDto> getTypeListByOnlySpId(Long spId);

    List<PartCargoDetailDto> getCargoDetailByPartName(String partName);

    int getByCodeListAndNoOut(List<String> cargoCodeList);
}
