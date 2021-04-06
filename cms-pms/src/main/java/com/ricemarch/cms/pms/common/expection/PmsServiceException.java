package com.ricemarch.cms.pms.common.expection;

import com.ricemarch.cms.pms.common.enums.PmsBaseErrorCodeEnum;

/**
 * @author tanwentao
 * @since 2021/3/11
 */

public class PmsServiceException extends BaseException {

    private String meesage;

    public PmsServiceException() {
    }

    public PmsServiceException(String message) {
        super(PmsBaseErrorCodeEnum.OPERATION_FAILED, message);
        this.meesage = message;
    }

    /**
     * 重新fillInStackTrace方法，不填充异常堆栈
     *
     * @return
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
