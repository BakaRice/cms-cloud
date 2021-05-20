package com.ricemarch.cms.pms.dto.wms;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 旭日图 item
 * @author RiceMarch
 * @date 2021/5/20 10:50
 */
@Data
@Accessors(chain = true)
public class SunburstItem {
    private String name;
    private ItemStyle itemStyle;
    private List<SunburstItem> children;
}
