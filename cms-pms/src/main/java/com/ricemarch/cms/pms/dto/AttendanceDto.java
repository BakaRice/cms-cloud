package com.ricemarch.cms.pms.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * @author RiceMarch
 * @date 2021/5/16 19:01
 */
@Data
@Accessors(chain = true)
public class AttendanceDto {
    private Long uid;
    private Long id;
    private LocalDate currDate;
    private String uName;
    private String sName;
    private Integer status;
    private String statusName;
}
/**
 * select a.id,
 * a.curr_date,
 * up.name,
 * st.name,
 * a.status,
 * case
 * when a.status = 0 then '0正常'
 * when a.status = 1 then '1迟到'
 * when a.status = 2 then '2早退'
 * when a.status = 3 then '3旷工'
 * when a.status = 4 then '4请假'
 * when a.status = 5 then '5出差'
 * else '-1数据异常'
 * END statusName
 * from attendance as a
 * left join user_pg up on up.id = a.user_id
 * left join scheduling_type st on st.id = a.schedule_type_id
 * where up.cell_id = ?
 * and up.institution_id = ?
 */