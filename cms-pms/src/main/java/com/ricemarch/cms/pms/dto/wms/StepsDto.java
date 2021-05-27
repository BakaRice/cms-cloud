package com.ricemarch.cms.pms.dto.wms;

import com.ricemarch.cms.pms.entity.MakeWorkBookSeq;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 用于工序的初始化
 *
 * @author RiceMarch
 * @date 2021/5/21 13:46
 */
@Data
@Slf4j
@Accessors(chain = true)
public class StepsDto {

    private String partName;

    private List<MakeWorkBookSeq> seqList;
}
