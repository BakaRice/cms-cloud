package com.ricemarch.cms.pms.common.facade;

import com.ricemarch.cms.pms.common.enums.BizEnum;
import com.ricemarch.cms.pms.common.enums.BizErrorCodeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author RiceMarch
 * @date 2021/3/7 16:23
 */
@Getter
@Setter
@ToString(callSuper = true)
public class BasePageResponse<T> extends BaseResponse<T> {
    private static final long serialVersionUID = 1L;

    public BasePageResponse() {
        super();
    }

    public BasePageResponse(BizEnum errorCode) {
        super(errorCode);
    }

    public BasePageResponse(BizEnum errorCode, String message) {
        super(errorCode, message);
    }

    public BasePageResponse(Integer offSet, Integer length, Integer totalRowNum) {
        super();
        this.offSet = offSet;
        this.length = length;
        this.totalRowNum = totalRowNum;
    }

    public BasePageResponse(BizEnum errorCode, Integer offSet, Integer length, Integer totalRowNum) {
        super(errorCode);
        this.offSet = offSet;
        this.length = length;
        this.totalRowNum = totalRowNum;
    }

    public BasePageResponse(T data, Integer offSet, Integer length, Integer totalRowNum) {
        super(data);
        this.offSet = offSet;
        this.length = length;
        this.totalRowNum = totalRowNum;
    }

    public BasePageResponse(BizEnum errorCode, String message, Integer offSet, Integer length, Integer totalRowNum) {
        super(errorCode, message);
        this.offSet = offSet;
        this.length = length;
        this.totalRowNum = totalRowNum;
    }

    public BasePageResponse(BizEnum errorCode, String message, T data, Integer offSet, Integer length,
                            Integer totalRowNum) {
        super(errorCode, message, data);
        this.offSet = offSet;
        this.length = length;
        this.totalRowNum = totalRowNum;
    }

    /**
     * 开始条数（原样返回）
     */
    @ApiModelProperty(value = "开始条数（原样返回）")
    private Integer offSet;

    /**
     * 本次查询条数（原样返回）
     */
    @ApiModelProperty(value = "本次查询条数（原样返回）")
    private Integer length;

    /**
     * 总数据条数
     */
    @ApiModelProperty(value = "总数据条数")
    private Integer totalRowNum;

    public static BasePageResponse success() {
        return new BasePageResponse();
    }

    public static BasePageResponse success(String message) {
        return new BasePageResponse(BizErrorCodeEnum.SUCCESS, message);
    }

    public static BasePageResponse success(Integer offSet, Integer length, Integer totalRowNum) {
        return new BasePageResponse(offSet, length, totalRowNum);
    }

    public static <T> BasePageResponse<T> success(T data, Integer offSet, Integer length, Integer totalRowNum) {
        return new BasePageResponse(data, offSet, length, totalRowNum);
    }

    public static <T> BasePageResponse<T> success(String message, Integer offSet, Integer length,
                                                  Integer totalRowNum) {
        return new BasePageResponse(BizErrorCodeEnum.SUCCESS, message, offSet, length, totalRowNum);
    }

    public static <T> BasePageResponse<T> success(String message, T data, Integer offSet, Integer length,
                                                  Integer totalRowNum) {
        return new BasePageResponse(BizErrorCodeEnum.SUCCESS, message, data, offSet, length, totalRowNum);
    }

    public static BasePageResponse operationFailed() {
        return new BasePageResponse(BizErrorCodeEnum.OPERATION_FAILED);
    }

    public static BasePageResponse operationFailed(String message) {
        return new BasePageResponse(BizErrorCodeEnum.OPERATION_FAILED, message);
    }

    public static <T> BasePageResponse<T> operationFailed(T data, Integer offSet, Integer length,
                                                          Integer totalRowNum) {
        return new BasePageResponse(BizErrorCodeEnum.OPERATION_FAILED, BizErrorCodeEnum.OPERATION_FAILED.getDesc(),
                data, offSet, length, totalRowNum);
    }

    public static <T> BasePageResponse<T> operationFailed(String message, Integer offSet, Integer length,
                                                          Integer totalRowNum) {
        return new BasePageResponse(BizErrorCodeEnum.OPERATION_FAILED, message, offSet, length, totalRowNum);
    }

    public static <T> BasePageResponse<T> operationFailed(String message, T data, Integer offSet, Integer length,
                                                          Integer totalRowNum) {
        return new BasePageResponse(BizErrorCodeEnum.OPERATION_FAILED, message, data, offSet, length, totalRowNum);
    }

    public static BasePageResponse systemError() {
        return new BasePageResponse(BizErrorCodeEnum.SYSTEM_ERROR);
    }

    public static BasePageResponse systemError(String message) {
        return new BasePageResponse(BizErrorCodeEnum.SYSTEM_ERROR, message);
    }

    public static <T> BasePageResponse<T> systemError(T data, Integer offSet, Integer length, Integer totalRowNum) {
        return new BasePageResponse(BizErrorCodeEnum.SYSTEM_ERROR, BizErrorCodeEnum.SYSTEM_ERROR.getDesc(), data,
                offSet, length, totalRowNum);
    }

    public static <T> BasePageResponse<T> systemError(String message, Integer offSet, Integer length,
                                                      Integer totalRowNum) {
        return new BasePageResponse(BizErrorCodeEnum.SYSTEM_ERROR, message, offSet, length, totalRowNum);
    }

    public static <T> BasePageResponse<T> systemError(String message, T data, Integer offSet, Integer length,
                                                      Integer totalRowNum) {
        return new BasePageResponse(BizErrorCodeEnum.SYSTEM_ERROR, message, data, offSet, length, totalRowNum);
    }

    public static BasePageResponse paramError() {
        return new BasePageResponse(BizErrorCodeEnum.PARAM_ERROR);
    }

    public static BasePageResponse paramError(String message) {
        return new BasePageResponse(BizErrorCodeEnum.PARAM_ERROR, message);
    }

    public static <T> BasePageResponse<T> paramError(T data, Integer offSet, Integer length, Integer totalRowNum) {
        return new BasePageResponse(BizErrorCodeEnum.PARAM_ERROR, BizErrorCodeEnum.PARAM_ERROR.getDesc(), data,
                offSet, length, totalRowNum);
    }

    public static <T> BasePageResponse<T> paramError(String message, Integer offSet, Integer length,
                                                     Integer totalRowNum) {
        return new BasePageResponse(BizErrorCodeEnum.PARAM_ERROR, message, offSet, length, totalRowNum);
    }

    public static <T> BasePageResponse<T> paramError(String message, T data, Integer offSet, Integer length,
                                                     Integer totalRowNum) {
        return new BasePageResponse(BizErrorCodeEnum.PARAM_ERROR, message, data, offSet, length, totalRowNum);
    }

    public static BasePageResponse paramIsNull() {
        return new BasePageResponse(BizErrorCodeEnum.PARAM_IS_NULL);
    }

    public static BasePageResponse paramIsNull(String message) {
        return new BasePageResponse(BizErrorCodeEnum.PARAM_IS_NULL, message);
    }

    public static <T> BasePageResponse<T> paramIsNull(T data, Integer offSet, Integer length, Integer totalRowNum) {
        return new BasePageResponse(BizErrorCodeEnum.PARAM_IS_NULL, BizErrorCodeEnum.PARAM_IS_NULL.getDesc(), data,
                offSet, length, totalRowNum);
    }

    public static <T> BasePageResponse<T> paramIsNull(String message, Integer offSet, Integer length,
                                                      Integer totalRowNum) {
        return new BasePageResponse(BizErrorCodeEnum.PARAM_IS_NULL, message, offSet, length, totalRowNum);
    }

    public static <T> BasePageResponse<T> paramIsNull(String message, T data, Integer offSet, Integer length,
                                                      Integer totalRowNum) {
        return new BasePageResponse(BizErrorCodeEnum.PARAM_IS_NULL, message, data, offSet, length, totalRowNum);
    }
}
