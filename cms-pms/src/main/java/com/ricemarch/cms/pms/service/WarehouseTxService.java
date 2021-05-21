package com.ricemarch.cms.pms.service;

import com.ricemarch.cms.pms.common.expection.PmsServiceException;
import com.ricemarch.cms.pms.common.facade.BaseResponse;
import com.ricemarch.cms.pms.dto.wms.InboundDto;
import com.ricemarch.cms.pms.dto.wms.OutboundDto;
import com.ricemarch.cms.pms.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author RiceMarch
 * @date 2021/5/20 13:22
 */
@Slf4j
@Service
public class WarehouseTxService {
    public static final Integer SPACE_PART_TYPE_CODE = 0;
    public static final Integer PART_TYPE_CODE = 1;

    @Autowired
    WarehouseService warehouseService;

    @Autowired
    WarehouseInboundService warehouseInboundService;

    @Autowired
    WarehouseOutboundService warehouseOutboundService;

    @Autowired
    WarehousePartService warehousePartService;

    @Autowired
    WarehouseSpacePartService warehouseSpacePartService;

    @Autowired
    WarehouseInboundDetailService inboundDetailService;

    @Transactional(rollbackFor = Exception.class)
    public BaseResponse postInboundService(String userName, Long userId, InboundDto inboundDto) {
        //判断仓库是否存在
        long wid = 0L;
        if (inboundDto.getWarehouse() == null) {
            throw new PmsServiceException("仓库信息不存在");
        }
        if (warehouseService.getById(wid = inboundDto.getWarehouse().getId()) == null) {
            throw new PmsServiceException("该仓库不存在");
        }
        //存入入库单
        WarehouseInbound warehouseInbound = new WarehouseInbound();
        warehouseInbound.setWarehouseId(wid).setCreateBy(userId).setCreateName(userName);
        boolean save = warehouseInboundService.save(warehouseInbound);
        if (!save) {
            throw new PmsServiceException("保存入库单失败");
        }
        Long id = warehouseInbound.getId();
        //存入 入库单明细
        List<WarehouseInboundDetail> warehouseInboundDetailList = inboundDto.getWarehouseInboundDetailList();
        warehouseInboundDetailList.forEach(u -> u.setWarehouseInboundId(id));
        try {
            inboundDetailService.saveBatch(warehouseInboundDetailList);
        } catch (Exception e) {
            throw new PmsServiceException(e.getMessage());
        }

        //存入成功 则 在对应的零件或备件仓库表中进行记录

        List<WarehousePart> warehousePartList = new ArrayList<>();
        List<WarehouseSpacePart> warehouseSpacePartList = new ArrayList<>();

        long finalWid = wid;
        warehouseInboundDetailList.stream()
                .filter(u -> u.getCargoType().equals(PART_TYPE_CODE))
                .forEach(w -> {
                    WarehousePart warehousePart = new WarehousePart();
                    warehousePart.setName(w.getCargoName());
                    warehousePart.setCode(w.getCargoCode());
                    warehousePart.setTypeCode(w.getCargoType());
                    warehousePart.setSupplierId(w.getCargoSupplierId());
                    warehousePart.setWarehouseId(finalWid);
                    warehousePart.setStatus(0L);//设置毛胚
                    warehousePart.setInTime(LocalDateTime.now());//入库随机
                    warehousePart.setIsDelete(0);
                    warehousePart.setCreateTime(LocalDateTime.now());
                    warehousePart.setUpdateTime(LocalDateTime.now());
                    warehousePart.setCreateBy(userId);
                    warehousePart.setCreateName(userName);
                    warehousePart.setInboudId(id);
                    warehousePartList.add(warehousePart);
                });

        warehouseInboundDetailList.stream()
                .filter(u -> u.getCargoType().equals(SPACE_PART_TYPE_CODE))
                .forEach(w -> {
                    WarehouseSpacePart warehouseSpacePart = new WarehouseSpacePart();
                    warehouseSpacePart.setName(w.getCargoName());
                    warehouseSpacePart.setCode(w.getCargoCode());
                    warehouseSpacePart.setTypeCode(w.getCargoType());
                    warehouseSpacePart.setSupplierId(w.getCargoSupplierId());
                    warehouseSpacePart.setWarehouseId(finalWid);
                    warehouseSpacePart.setStatus(0L);
                    warehouseSpacePart.setInTime(LocalDateTime.now());
                    warehouseSpacePart.setIsDelete(0);
                    warehouseSpacePart.setCreateTime(LocalDateTime.now());
                    warehouseSpacePart.setCreateBy(userId);
                    warehouseSpacePart.setCreateName(userName);
                    warehouseSpacePart.setInboundId(id);
                    warehouseSpacePartList.add(warehouseSpacePart);
                });
        try {
            boolean b = warehousePartService.saveBatch(warehousePartList);
            boolean b1 = warehouseSpacePartService.saveBatch(warehouseSpacePartList);
            if (warehouseSpacePartList.size() == 0) {
                b1 = true;
            }
            if (warehousePartList.size() == 0) {
                b = true;
            }
            if (b && b1) {
                log.info("零件入库成功");
            } else {
                throw new PmsServiceException("零件入库失败");
            }
        } catch (Exception e) {
            throw new PmsServiceException(e.getMessage());
        }
        return new BaseResponse();
    }

    @Transactional(rollbackFor = Exception.class)
    public BaseResponse postOutboundService(String userName, Long userId, OutboundDto outboundDto) {
        //判断仓库是否存在
        long wid = 0L;
        if (outboundDto.getWarehouse() == null) {
            throw new PmsServiceException("仓库信息不存在");
        }
        if (warehouseService.getById(wid = outboundDto.getWarehouse().getId()) == null) {
            throw new PmsServiceException("该仓库不存在");
        }
        //存入入库单
        WarehouseOutbound warehouseOutbound = new WarehouseOutbound();
        warehouseOutbound.setWarehouseId(wid).setCreateBy(userId).setCreateName(userName);
        boolean save = warehouseOutboundService.save(warehouseOutbound);
        if (!save) {
            throw new PmsServiceException("保存出库单失败");
        }
        Long id = warehouseOutbound.getId();

        if (CollectionUtils.isEmpty(outboundDto.getCargoCodeList())) {
            throw new PmsServiceException("出库单列表不能为空");
        }
        int s = warehousePartService.getByCodeListAndNoOut(outboundDto.getCargoCodeList());
        if (s != outboundDto.getCargoCodeList().size()) {
            throw new PmsServiceException("出库单列表存在已出库或不存在零件，无法出库");
        }
        try {

            int i = warehouseOutboundService.outByIdList(outboundDto.getCargoCodeList(), id, userName, userId);
        } catch (Exception ex) {
            throw new PmsServiceException("出库单列表存在错误零件编号，无法出库");
        }
        return new BaseResponse();
    }
}
