package com.ricemarch.cms.pms.common.facade;

import com.ricemarch.cms.pms.common.enums.BizEnum;
import com.ricemarch.cms.pms.common.enums.BizErrorCodeEnum;

import java.io.Serializable;

/**
 * @author RiceMarch
 * @date 2021/3/7 16:23
 */
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;
    private String message;
    private T data;

    /**
     * 成功返回 无结果
     */
    public BaseResponse() {
        this(BizErrorCodeEnum.SUCCESS, BizErrorCodeEnum.SUCCESS.getDesc());
    }

    /**
     * 错误返回
     *
     * @param errorCode 错误枚举类型
     */
    public BaseResponse(BizEnum errorCode) {
        this(errorCode, errorCode.getDesc());
    }

    /**
     * 成功返回
     *
     * @param data 携带数据
     */
    public BaseResponse(T data) {
        this(BizErrorCodeEnum.SUCCESS, BizErrorCodeEnum.SUCCESS.getDesc(), data);
    }

    /**
     * 错误返回
     *
     * @param errorCode 错误代码
     * @param message   错误信息
     */
    public BaseResponse(BizEnum errorCode, String message) {
        this(errorCode, message, null);
    }

    public BaseResponse(BizEnum errorCode, String message, T data) {
        this.code = errorCode.getCode();
        this.message = message;
        this.data = data;
    }

    public static BaseResponse success() {
        return new BaseResponse();
    }

    public static BaseResponse success(String message) {
        return new BaseResponse(BizErrorCodeEnum.SUCCESS, message);
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(data);
    }

    public static <T> BaseResponse<T> success(String message, T data) {
        return new BaseResponse(BizErrorCodeEnum.SUCCESS, message, data);
    }

    public static BaseResponse operationFailed() {
        return new BaseResponse(BizErrorCodeEnum.OPERATION_FAILED);
    }

    public static BaseResponse operationFailed(String message) {
        return new BaseResponse(BizErrorCodeEnum.OPERATION_FAILED, message);
    }

    public static <T> BaseResponse<T> operationFailed(T data) {
        return new BaseResponse(BizErrorCodeEnum.OPERATION_FAILED, BizErrorCodeEnum.OPERATION_FAILED.getDesc(),
                data);
    }

    public static <T> BaseResponse<T> operationFailed(String message, T data) {
        return new BaseResponse(BizErrorCodeEnum.OPERATION_FAILED, message, data);
    }

    public static BaseResponse systemError() {
        return new BaseResponse(BizErrorCodeEnum.SYSTEM_ERROR);
    }

    public static BaseResponse systemError(String message) {
        return new BaseResponse(BizErrorCodeEnum.SYSTEM_ERROR, message);
    }

    public static <T> BaseResponse<T> systemError(T data) {
        return new BaseResponse<>(BizErrorCodeEnum.SYSTEM_ERROR, BizErrorCodeEnum.SYSTEM_ERROR.getDesc(), data);
    }

    public static <T> BaseResponse<T> systemError(String message, T data) {
        return new BaseResponse(BizErrorCodeEnum.SYSTEM_ERROR, message, data);
    }

    public static BaseResponse paramError() {
        return new BaseResponse(BizErrorCodeEnum.PARAM_ERROR);
    }

    public static BaseResponse paramError(String message) {
        return new BaseResponse(BizErrorCodeEnum.PARAM_ERROR, message);
    }

    public static <T> BaseResponse<T> paramError(T data) {
        return new BaseResponse(BizErrorCodeEnum.PARAM_ERROR, BizErrorCodeEnum.PARAM_ERROR.getDesc(), data);
    }

    public static <T> BaseResponse<T> paramError(String message, T data) {
        return new BaseResponse(BizErrorCodeEnum.PARAM_ERROR, message, data);
    }

    public static BaseResponse paramIsNull() {
        return new BaseResponse(BizErrorCodeEnum.PARAM_IS_NULL);
    }

    public static BaseResponse paramIsNull(String message) {
        return new BaseResponse(BizErrorCodeEnum.PARAM_IS_NULL, message);
    }

    public static <T> BaseResponse<T> paramIsNull(T data) {
        return new BaseResponse(BizErrorCodeEnum.PARAM_IS_NULL, BizErrorCodeEnum.PARAM_IS_NULL.getDesc(), data);
    }

    public static <T> BaseResponse<T> paramIsNull(String message, T data) {
        return new BaseResponse(BizErrorCodeEnum.PARAM_IS_NULL, message, data);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

