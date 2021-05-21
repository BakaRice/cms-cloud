package com.ricemarch.cms.pms.dto.wms;

import com.ricemarch.cms.pms.entity.Warehouse;
import com.ricemarch.cms.pms.entity.WarehouseInboundDetail;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author RiceMarch
 * @date 2021/5/20 12:22
 */
@Data
@Accessors(chain = true)
public class InboundDto {
    private Warehouse warehouse;
    private List<WarehouseInboundDetail> warehouseInboundDetailList;
}
