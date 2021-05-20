package com.ricemarch.cms.pms.controller;

import com.ricemarch.cms.pms.common.facade.BaseResponse;
import com.ricemarch.cms.pms.dto.wms.SunburstItem;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author RiceMarch
 * @date 2021/5/16 16:29
 */
@Slf4j
@RestController
@RequestMapping("/api/pms/warehouse")
public class WarehouseController {

    @ApiOperation("旭日图")
    @GetMapping("Sunburst")
    public BaseResponse<List<SunburstItem>> getSunburst() {
        return new BaseResponse<List<SunburstItem>>();
    }


    @ApiOperation("入库")
    @PostMapping("inbound")
    public BaseResponse postInbound() {

        return new BaseResponse();
    }

    @ApiOperation("出库")
    @PostMapping("outbound")
    public BaseResponse postOutbound() {
        return new BaseResponse();
    }

}
