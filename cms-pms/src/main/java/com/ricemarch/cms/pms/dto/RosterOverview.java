package com.ricemarch.cms.pms.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 当日派工overview
 *
 * @author RiceMarch
 * @date 2021/5/16 13:50
 */
@Data
@Accessors(chain = true)
public class RosterOverview {

    //员工总数
    private Integer userCount;

    //未安排总数
    private Integer unDealUserCount;

    //白班
    private Integer dayUserCount;

    //夜班
    private Integer nightUserCount;

}
