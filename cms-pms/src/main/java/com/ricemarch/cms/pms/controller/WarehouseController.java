package com.ricemarch.cms.pms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.ricemarch.cms.pms.common.component.ColorUtils;
import com.ricemarch.cms.pms.dto.wms.*;

import com.ricemarch.cms.pms.common.expection.PmsServiceException;
import com.ricemarch.cms.pms.common.facade.BaseResponse;
import com.ricemarch.cms.pms.entity.*;
import com.ricemarch.cms.pms.service.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author RiceMarch
 * @date 2021/5/16 16:29
 */
@Slf4j
@RestController
@RequestMapping("/api/pms/warehouse")
public class WarehouseController extends BaseController {

    public static final Integer SPACE_PART_TYPE_CODE = 0;
    public static final Integer PART_TYPE_CODE = 1;

    @Autowired
    WarehouseService warehouseService;

    @Autowired
    WarehouseInboundService warehouseInboundService;

    @Autowired
    WarehousePartService warehousePartService;

    @Autowired
    WarehouseSpacePartService warehouseSpacePartService;

    @Autowired
    WarehouseInboundDetailService inboundDetailService;

    @Autowired
    WarehouseTxService warehouseTxService;

    @Autowired
    MakeWorkBookSeqService makeWorkBookSeqService;

    @ApiOperation("[push]新建仓库")
    @PostMapping
    public BaseResponse<Boolean> postWarehouse(@RequestBody Warehouse warehouse) {
        //从token中获取
        Long cellId = getCellId();
        Long institutionId = getInstitutionId();
        Integer roleId = getRoleId();
        Long userId = getUserId();
        String userName = getUserName();

        warehouse.setWarehouseOwner(userId);
        warehouse.setWarehouseOwnerName(userName);
        warehouse.setWarehouseOwnerPhone("");

        if (warehouseService.save(warehouse)) {
            return new BaseResponse<>(Boolean.TRUE);
        } else {
            throw new PmsServiceException("创建仓库异常");
        }
    }

    @ApiOperation("仓库列表")
    @GetMapping
    public BaseResponse<List<Warehouse>> getWarehouse() {
        List<Warehouse> warehouseList = warehouseService.getAll();
        return new BaseResponse<>(warehouseList);
    }

    @ApiOperation("旭日图")
    @GetMapping("Sunburst")
    public BaseResponse<List<SunburstItem>> getSunburst() {
        //三级旭日图
        List<SunburstItem> list = new ArrayList<>();
        //类型
        //供应商
        //供应商下属具体种类名称

        SunburstColorDto sc = new SunburstColorDto(10, 163, 181, 50, 240, 161);
        SunburstItem sunburstItem = new SunburstItem();
        sunburstItem.setName("备件");
        sunburstItem.setValue(1);
        int spv = 0;
        sunburstItem.setItemStyle(new ItemStyle(ColorUtils.rc(sc.getR(), sc.getG(), sc.getB(), sc.getR1(), sc.getG1(), sc.getB1())));
        //获取备件供应商列表 此时获取的供应商列表 children依然为空 还要进一步组装
        List<SunburstItem<WarehouseSupplier>> serviceSupplierList = warehouseSpacePartService.getSupplierList(sc.getR(), sc.getG(), sc.getB(), sc.getR1(), sc.getG1(), sc.getB1());
        for (SunburstItem<WarehouseSupplier> warehouseSupplierSunburstItem : serviceSupplierList) {
            WarehouseSupplier data = warehouseSupplierSunburstItem.getData();
            Long spId = data.getId();
            List<SunburstItem> warehouseSpacePartList = warehouseSpacePartService.getTypeListBySpId(spId, sc.getR(), sc.getG(), sc.getB(), sc.getR1(), sc.getG1(), sc.getB1());
            warehouseSupplierSunburstItem.setChildren(warehouseSpacePartList);
            warehouseSupplierSunburstItem.setValue(warehouseSpacePartList.size());
            spv += warehouseSpacePartList.size();
        }
        //设置备件供应商列表 第二级
        sunburstItem.setChildren(serviceSupplierList);
        sunburstItem.setValue(spv);

        //查询所有零件的供应商list
        SunburstItem sunburstItem1 = new SunburstItem();
        sunburstItem1.setName("零件");
        sunburstItem1.setItemStyle(new ItemStyle(ColorUtils.rc(sc.getR(), sc.getG(), sc.getB(), sc.getR1(), sc.getG1(), sc.getB1())));
        int pv = 0;
        sunburstItem1.setChildren(Lists.newArrayList());
        List<SunburstItem<WarehouseSupplier>> warehouseSpacePartServiceSupplierList = warehousePartService.getSupplierList(sc.getR(), sc.getG(), sc.getB(), sc.getR1(), sc.getG1(), sc.getB1());
        //此时获取的供应商列表 children依然为空 还要进一步组装
        for (SunburstItem<WarehouseSupplier> sunburstItem2 : warehouseSpacePartServiceSupplierList) {
            WarehouseSupplier data = sunburstItem2.getData();
            Long spId = data.getId();
            List<SunburstItem> warehouseSpacePartList = warehousePartService.getTypeListBySpId(spId, sc.getR(), sc.getG(), sc.getB(), sc.getR1(), sc.getG1(), sc.getB1());
            sunburstItem2.setChildren(warehouseSpacePartList);
            sunburstItem2.setValue(warehouseSpacePartList.size());
            pv += warehouseSpacePartList.size();
        }
        sunburstItem1.setChildren(warehouseSpacePartServiceSupplierList);
        sunburstItem1.setValue(pv);

        list.add(sunburstItem);
        list.add(sunburstItem1);

        return new BaseResponse<>(list);
    }

    @ApiOperation("根据spId查找对应所有零件种类列表")
    @GetMapping("part")
    public BaseResponse<PageInfo<PartCargoDto>> getPartPage(@RequestParam Long spId, @RequestParam @NotNull int pageNum, @RequestParam int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<PartCargoDto> list = warehousePartService.getTypeListByOnlySpId(spId);

        PageInfo<PartCargoDto> partCargoDtoPageInfo = new PageInfo<>(list);
        return new BaseResponse<>(partCargoDtoPageInfo);
    }

    @ApiOperation("根据查找条件查找对应所有零件种类列表")
    @GetMapping("space-part")
    public BaseResponse<PageInfo<PartCargoDetailDto>> getSpacePartPage() {

        return new BaseResponse<>();
    }


    @ApiOperation("根据零件种类查询零件库存详情列表")
    @GetMapping("cargo-detail")
    public BaseResponse<PageInfo> getCargoDetailPage(@RequestParam String partName, @RequestParam @NotNull int pageNum, @RequestParam int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<PartCargoDetailDto> list = warehousePartService.getCargoDetailByPartName(partName);
        PageInfo<PartCargoDetailDto> partCargoDetailDtoPageInfo = new PageInfo<>(list);
        return new BaseResponse<>(partCargoDetailDtoPageInfo);
    }

    @ApiOperation("入库")
    @PostMapping("inbound")
    public BaseResponse postInbound(@RequestBody InboundDto inboundDto) {
        String userName = getUserName();
        Long userId = getUserId();
        //转入事务 保证数据的一致性
        return warehouseTxService.postInboundService(userName, userId, inboundDto);

    }

    @ApiOperation("零件出库")
    @PostMapping("outbound")
    public BaseResponse postOutbound(@RequestBody OutboundDto outboundDto) {
        /**
         * 给一个 cargo code list 直接进行出库即可！
         */
        String userName = getUserName();
        Long userId = getUserId();
        //转入事务 保证数据的一致性
        return warehouseTxService.postOutboundService(userName, userId, outboundDto);
    }

    @ApiOperation("根据cargocode判断零件是否在仓库内")
    public BaseResponse<Boolean> getPartByCargoCode(@RequestParam String cargoCode) {
        int byCodeListAndNoOut = warehousePartService.getByCodeListAndNoOut(List.of(cargoCode));
        return new BaseResponse<>(byCodeListAndNoOut == 1);
    }


    @ApiOperation("查询所有零件和备件种类名称")
    @GetMapping("/all-type")
    public BaseResponse<List> getAllType() {
        List<String> partTypeList = warehousePartService.getAllType();
        List<String> spacePartTypeList = warehouseSpacePartService.getAllType();
        for (String s : spacePartTypeList) {
            partTypeList.add(s);
        }
        return new BaseResponse<>(partTypeList);
    }

    @ApiOperation("分页查找零件列表，包含其是否有初始化工序")
    @GetMapping("/part/make")
    public BaseResponse<PageInfo<PartSeqDto>> getAllPartWithMakeInfo(@RequestParam @NotNull int pageNum, @RequestParam int pageSize) {
        PageInfo<PartSeqDto> seqDtoPageInfo = makeWorkBookSeqService.getPagePartWithMakeInfo(pageNum, pageSize);
        return new BaseResponse<>(seqDtoPageInfo);
    }
}
