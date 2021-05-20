package com.ricemarch.cms.pms.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author RiceMarch
 * @date 2021/5/16 20:16
 */
@Data
@Accessors(chain = true)
public class AttendancesOverview {
    //-- /        考勤状态(0正常，1迟到，2早退，3旷工，4请假，5出差）
    private Integer all;
    private Integer success; //0
    private Integer late; //1
    private Integer early; // 2
    private Integer undeal; //3
    private Integer off; // 4
    private Integer trip; //5
}
