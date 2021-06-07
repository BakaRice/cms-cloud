package com.ricemarch.cms.pms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ricemarch.cms.pms.dto.AttendanceDto;
import com.ricemarch.cms.pms.dto.wms.CargoNumDto;
import com.ricemarch.cms.pms.dto.wms.SupplierOverviewDto;
import com.ricemarch.cms.pms.entity.WarehouseSupplier;
import com.ricemarch.cms.pms.mapper.WarehouseSupplierMapper;
import com.ricemarch.cms.pms.service.WarehouseSupplierService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 供应商 服务实现类
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-20
 */
@Service
public class WarehouseSupplierServiceImpl extends ServiceImpl<WarehouseSupplierMapper, WarehouseSupplier> implements WarehouseSupplierService {


    @Autowired
    WarehouseSupplierMapper warehouseSupplierMapper;

    @Override
    public PageInfo<SupplierOverviewDto> getSupplierOverviewDto(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SupplierOverviewDto> supplierOverviewDtoList = warehouseSupplierMapper.getAllList();
        PageInfo<SupplierOverviewDto> pageInfo = new PageInfo<>(supplierOverviewDtoList);
        List<Long> supplierIdList = pageInfo.getList().stream().map(e -> e.getSupplierId()).collect(Collectors.toList());
        List<CargoNumDto> partTypeNumList = warehouseSupplierMapper.getPartTotalTypeNumList(supplierIdList);
        List<CargoNumDto> spacePartTypeNumList = warehouseSupplierMapper.getSpacePartTotalTypeNumList(supplierIdList);

        List<CargoNumDto> partNumList = warehouseSupplierMapper.getPartTotalNumList(supplierIdList);
         List<CargoNumDto> spacePartNumList = warehouseSupplierMapper.getSpacePartTotalNumList(supplierIdList);

        Map<Long, Long> partTypeNumMap = partTypeNumList.stream().collect(Collectors.toMap(CargoNumDto::getSupplierId, CargoNumDto::getNum));
        Map<Long,Long> spacePartTypeNumMap = spacePartTypeNumList.stream().collect(Collectors.toMap(CargoNumDto::getSupplierId, CargoNumDto::getNum));
        Map<Long,Long> partNumMap = partNumList.stream().collect(Collectors.toMap(CargoNumDto::getSupplierId, CargoNumDto::getNum));
        Map<Long,Long> spacePartNumMap = spacePartNumList.stream().collect(Collectors.toMap(CargoNumDto::getSupplierId, CargoNumDto::getNum));
        CargoNumDto zeroCargo = new CargoNumDto();
        zeroCargo.setNum(0L);
        for (SupplierOverviewDto supplierOverviewDto : pageInfo.getList()) {
            Long supplierId = supplierOverviewDto.getSupplierId();

            Long num = partTypeNumMap.getOrDefault(supplierId, 0L) + spacePartTypeNumMap.getOrDefault(supplierId, 0L);
            Long totalNum = partNumMap.getOrDefault(supplierId, 0L) + spacePartNumMap.getOrDefault(supplierId, 0L);
            supplierOverviewDto.setTotalTypeNum(num);
            supplierOverviewDto.setTotalNum(totalNum);
        }
        return pageInfo;
    }

    @Override
    public List<WarehouseSupplier> getAll() {
      return   warehouseSupplierMapper.getAll();
    }
}
