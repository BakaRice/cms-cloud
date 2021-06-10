package com.ricemarch.cms.pms.dto.wms;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author RiceMarch
 * @date 2021/6/10 10:39
 */
@Data
@Accessors(chain = true)
public class StreamDto {
    private Long id;
    private String type;
    private String userName;
    private String uid;
    private Long warehouseId;
    private String warehouseName;
    private LocalDateTime createTime;
}
