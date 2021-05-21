package com.ricemarch.cms.pms.dto.wms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author RiceMarch
 * @date 2021/5/20 16:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SunburstColorDto {
    private int r;
    private int g;
    private int b;
    private int r1;
    private int g1;
    private int b1;
}
