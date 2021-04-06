package com.ricemarch.cms.pms.common.enums;

/**
 * @author RiceMarch
 * @date 2021/3/21 15:25
 */
public enum WorkScheduleEnum {

    /**
     * 早班
     */
    FIRST(1, "早班", "早班为8:00～16:00"),
    /**
     * 中班
     */
    SECOND(2, "中班", "中班为16：00～23:00"),
    /**
     * 晚班
     */
    THIRD(3, "晚班", "夜班为23:00～4:00");


    private final int code;
    private final String name;
    private final String desc;

    WorkScheduleEnum(int code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }
}
