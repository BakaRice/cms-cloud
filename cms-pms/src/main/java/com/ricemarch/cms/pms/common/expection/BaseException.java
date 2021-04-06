package com.ricemarch.cms.pms.common.expection;

import com.ricemarch.cms.pms.common.enums.BizEnum;
import com.ricemarch.cms.pms.common.enums.BizErrorCodeEnum;

import java.io.Serializable;

/**
 * 基础异常类
 *
 * @author tanwentao
 * @since 2021/3/11
 */

public class BaseException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 2728936692069322518L;

    private BizEnum errorCode;

    private String errorMessage;

    public BaseException() {
        super(BizErrorCodeEnum.OPERATION_FAILED.getDesc());
        this.errorCode = BizErrorCodeEnum.OPERATION_FAILED;
        this.errorMessage = errorCode.getDesc();

    }

    public BaseException(BizEnum errorCode) {
        super(errorCode.getDesc());
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDesc();
    }

    public BaseException(BizEnum errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BaseException(BizEnum errorCode, String errorMessage, Throwable exception) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        super.initCause(exception);
    }

    public BizEnum getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorCode(BizEnum errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static boolean isBizException(Throwable exception) {

        return exception instanceof BaseException;
    }

    public static boolean isErrorException(BizEnum errorCode) {

        return BizErrorCodeEnum.SYSTEM_ERROR.equals(errorCode) || BizErrorCodeEnum.CALL_SERVICCE_ERROR.equals(errorCode)
                || BizErrorCodeEnum.CALL_SERVICCE_ERROR.equals(errorCode)
                || BizErrorCodeEnum.REQUEST_ERROR.equals(errorCode)
                || BizErrorCodeEnum.PROCESS_FAIL.equals(errorCode);
    }

}
