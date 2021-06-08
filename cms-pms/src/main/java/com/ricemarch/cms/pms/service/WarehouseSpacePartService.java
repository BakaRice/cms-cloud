package com.ricemarch.cms.pms.service;

import com.ricemarch.cms.pms.dto.wms.SunburstItem;
import com.ricemarch.cms.pms.entity.WarehouseSpacePart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ricemarch.cms.pms.entity.WarehouseSupplier;

import java.util.List;

/**
 * <p>
 * 仓库备件记录 服务类
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-20
 */
public interface WarehouseSpacePartService extends IService<WarehouseSpacePart> {

    List<SunburstItem> getTypeListBySpId(Long spId,int r, int g, int b, int r1, int g1, int b1);

    List<SunburstItem<WarehouseSupplier>> getSupplierList(int r, int g, int b, int r1, int g1, int b1);

    List<String> getAllType();

    Integer getByCodeListAndNoOut(List<String> cargoCodeList);
}
