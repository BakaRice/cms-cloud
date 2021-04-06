package com.ricemarch.cms.pms.dto;

public enum UserType {
    /**
     * 默认员工
     */
    DEFAULT("default"),
    /**
     * 机构员工
     */
    INSTITUTION_LEADER("institution_leader"),
    /**
     * 公司
     */
    COMPANY_LEADER("company_leader"),
    /**
     * 最高权限
     */
    ADMIN("admin");
    /**
     * 员工类型
     */
    private final String TYPE;

    UserType(String type) {
        TYPE = type;
    }

    public String getType() {
        return TYPE;
    }
}
