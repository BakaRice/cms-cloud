package com.ricemarch.cms.pms.service.impl;

import com.google.common.collect.Lists;
import com.ricemarch.cms.pms.common.component.ColorUtils;
import com.ricemarch.cms.pms.dto.wms.ItemStyle;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ricemarch.cms.pms.dto.wms.PartCargoDetailDto;
import com.ricemarch.cms.pms.dto.wms.PartCargoDto;
import com.ricemarch.cms.pms.dto.wms.SunburstItem;
import com.ricemarch.cms.pms.entity.WarehousePart;
import com.ricemarch.cms.pms.entity.WarehouseSpacePart;
import com.ricemarch.cms.pms.entity.WarehouseSupplier;
import com.ricemarch.cms.pms.mapper.WarehousePartMapper;
import com.ricemarch.cms.pms.mapper.WarehouseSupplierMapper;
import com.ricemarch.cms.pms.service.WarehousePartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 仓库零件记录 服务实现类
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-20
 */
@Service
public class WarehousePartServiceImpl extends ServiceImpl<WarehousePartMapper, WarehousePart> implements WarehousePartService {

    @Autowired
    WarehouseSupplierMapper warehouseSupplierMapper;

    @Autowired
    WarehousePartMapper warehousePartMapper;


    @Override
    public List<SunburstItem<WarehouseSupplier>> getSupplierList(int r, int g, int b, int r1, int g1, int b1) {
        List<SunburstItem<WarehouseSupplier>> list = new ArrayList<>();
        List<WarehouseSupplier> warehouseSuppliers = warehouseSupplierMapper.getSupplierList();
        int i = 0;
        for (WarehouseSupplier warehouseSupplier : warehouseSuppliers) {
            String rc = ColorUtils.rc(r, g, b, r1, g1, b1);
            SunburstItem<WarehouseSupplier> sunburstItem = new SunburstItem<>();
            //"零件SP" + warehouseSupplier.getSupplierName()
            sunburstItem.setName(warehouseSupplier.getSupplierName());
            sunburstItem.setItemStyle(new ItemStyle(rc));
            sunburstItem.setData(warehouseSupplier);
//            sunburstItem.setValue(++i);
            sunburstItem.setChildren(Lists.newArrayList());
            list.add(sunburstItem);
        }
        return list;
    }

    @Override
    public List<SunburstItem> getTypeListBySpId(Long spId, int r, int g, int b, int r1, int g1, int b1) {
        List<String> typeListBySpId = warehousePartMapper.getTypeListBySpId(spId);
        List<SunburstItem> list = new ArrayList<>();
        int i = 0;
        for (String name : typeListBySpId) {
            String rc = ColorUtils.rc(r, g, b, r1, g1, b1);
            SunburstItem<WarehouseSpacePart> item = new SunburstItem<>();
            item.setChildren(null);
            //"零件" + name
            item.setName(name);
            item.setItemStyle(new ItemStyle(rc));
//            item.setValue(++i);
//            item.setData(warehouseSpacePart);
            list.add(item);
        }
        return list;
    }

    @Override
    public List<PartCargoDto> getTypeListByOnlySpId(Long spId) {
        return warehousePartMapper.getTypeListByOnlySpId(spId);
    }

    @Override
    public List<PartCargoDetailDto> getCargoDetailByPartName(String partName) {
        return warehousePartMapper.getCargoDetailByPartName(partName);
    }

    @Override
    public int getByCodeListAndNoOut(List<String> cargoCodeList) {
        return warehousePartMapper.getByCodeListAndNoOut(cargoCodeList);
    }

    @Override
    public WarehousePart getByName(String partName) {
        WarehousePart warehousePart = warehousePartMapper.getByName(partName);
        return warehousePart;
    }
}
