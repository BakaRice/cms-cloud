package com.ricemarch.cms.pms.common.component;

import com.ricemarch.cms.pms.common.expection.PmsServiceException;
import com.ricemarch.cms.pms.entity.QualityPartClaim;
import com.ricemarch.cms.pms.entity.WarehousePart;
import com.ricemarch.cms.pms.service.QualityPartClaimService;
import com.ricemarch.cms.pms.service.WarehousePartService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author RiceMarch
 * @date 2021/5/21 13:24
 */
@Service
public class WorkInitUtils {

    @Autowired
    QualityPartClaimService qualityPartClaimService;

    public static final String CHECK_LEVEL_1 = "0000";
    public static final String CHECK_LEVEL_2 = "0001";
    public static final String CHECK_LEVEL_3 = "0010";

    public static String initWorkId(String code) {
        String date = LocalDateTime.now().toLocalDate().toString();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return code + date + uuid;
    }

    public static String initWorkId() {
        String date = LocalDateTime.now().toLocalDate().toString();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return "WITHOUTCODE" + date + uuid;
    }

    public static String initWorkBookNo(String name) {
        String date = LocalDateTime.now().toLocalDate().toString();
        return "WB-" + name + "-" + date + "-" + UUID.randomUUID();
    }

    /**
     * 初始化 加工流转状态码
     * 检查级别设定的维度是零件类型维度 对于工序无影响
     *
     * @param code 零件编号
     * @return
     */
    public String initFlowCode(String code) {
        StringBuffer sb = new StringBuffer();
//        WarehousePart warehousePart = partService.getByCode(code);
        String level = qualityPartClaimService.getByPartCode(code);
        //toDO 通过CODE => NAME => LEVEL
        if (level.length() != 4) {
            throw new PmsServiceException("零件初始化 检查级别获取失败!");
        }
        sb.append(level);
        sb.append("0000 0000 0000 0000 0000 0000");
        sb.append("0000");
        String initBin = sb.toString();
        String hex = HexUtils.binToHex(initBin);
        return hex;
    }
}
