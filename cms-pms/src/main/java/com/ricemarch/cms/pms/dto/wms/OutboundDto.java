package com.ricemarch.cms.pms.dto.wms;

import com.ricemarch.cms.pms.entity.Warehouse;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author RiceMarch
 * @date 2021/5/21 9:50
 */
@Data
@Slf4j
@Accessors(chain = true)
public class OutboundDto {
    private Warehouse warehouse;

    private List<String> cargoCodeList;
}
