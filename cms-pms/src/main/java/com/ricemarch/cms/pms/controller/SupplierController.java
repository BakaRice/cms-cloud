package com.ricemarch.cms.pms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ricemarch.cms.pms.common.expection.PmsServiceException;
import com.ricemarch.cms.pms.common.facade.BaseResponse;
import com.ricemarch.cms.pms.dto.wms.SupplierOverviewDto;
import com.ricemarch.cms.pms.entity.WarehouseSupplier;
import com.ricemarch.cms.pms.service.WarehouseSupplierService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author RiceMarch
 * @date 2021/5/20 12:40
 */
@Slf4j
@RestController
@RequestMapping("/api/pms/supplier")
public class SupplierController {

    @Autowired
    WarehouseSupplierService warehouseSupplierService;

    @ApiOperation("[push]新建供应商")
    @PostMapping
    public BaseResponse<Boolean> postSupplier(@RequestBody WarehouseSupplier supplier) {
        boolean save;
        try {
            save = warehouseSupplierService.save(supplier);
        } catch (Exception e) {
            throw new PmsServiceException(e.getMessage());
        }
        return new BaseResponse<>(save);
    }

    @ApiOperation("获取供应商列表")
    @GetMapping
    public BaseResponse<PageInfo<SupplierOverviewDto>> getSuppliers(@RequestParam @NotNull int pageNum, @RequestParam int pageSize) {
        PageInfo<SupplierOverviewDto> supplierOverviewDtoPageInfo = warehouseSupplierService.getSupplierOverviewDto(pageNum, pageSize);
        return new BaseResponse<>(supplierOverviewDtoPageInfo);
    }

    @ApiOperation("获取所有供应商列表")
    @GetMapping("/all")
    public BaseResponse<List<WarehouseSupplier>> getAllSupplier() {
        List<WarehouseSupplier> warehouseSuppliers = warehouseSupplierService.getAll();
        return new BaseResponse<>(warehouseSuppliers);
    }

}
