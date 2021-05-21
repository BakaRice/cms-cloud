package com.ricemarch.cms.pms.controller;

import com.ricemarch.cms.pms.common.expection.PmsServiceException;
import com.ricemarch.cms.pms.common.facade.BaseResponse;
import com.ricemarch.cms.pms.entity.WarehouseSupplier;
import com.ricemarch.cms.pms.service.WarehouseSupplierService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
