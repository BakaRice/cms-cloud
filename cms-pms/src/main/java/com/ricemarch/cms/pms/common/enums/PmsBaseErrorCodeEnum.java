package com.ricemarch.cms.pms.common.enums;

/**
 * @author tanwentao
 * @since 2021/3/11
 */

public enum PmsBaseErrorCodeEnum implements BizEnum {
    /**
     * 操作失败
     */
    OPERATION_FAILED(4000, "OPERATION_FAILED", "操作失败");

    private final int code;
    private final String name;
    private final String desc;

    PmsBaseErrorCodeEnum(int code, String name, String desc) {

        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }
}
