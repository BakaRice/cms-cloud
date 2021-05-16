package com.ricemarch.cms.pms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * 排班 dto -> vo 直接传输给前端使用的
 *
 * @author RiceMarch
 * @date 2021/5/16 11:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Roster  implements Comparable<Roster>{
    private String uid;
    private String name;
    private LocalDate startTime;
    private LocalDate endTime;
    /**
     * scheduleTypeId
     */
    private Integer type;
    private String typeName;


    @Override
    public int compareTo(Roster roster) {
        return this.getStartTime().compareTo(roster.getStartTime());
    }
}
