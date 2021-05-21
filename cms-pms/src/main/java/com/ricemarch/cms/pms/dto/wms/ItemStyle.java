package com.ricemarch.cms.pms.dto.wms;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author RiceMarch
 * @date 2021/5/20 10:53
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ItemStyle {
    private String color;

    public ItemStyle(String color) {
        this.color = color;
    }
}
