package com.ricemarch.cms.pms.common.expection;

import com.ricemarch.cms.pms.common.enums.BizErrorCodeEnum;
import com.ricemarch.cms.pms.common.facade.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author RiceMarch
 * @date 2021/3/7 18:25
 */
@Slf4j
//声明捕获统一的异常处理
@RestControllerAdvice
public class ExceptionController {

    String exceptionMsg = "[统一异常拦截器]";

    /**
     * 属性校验失败异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handleValidException(MethodArgumentNotValidException exception) {
        StringBuilder strBuilder = new StringBuilder();
        exception.getBindingResult()
                .getFieldErrors()
                .forEach(e -> {
                    strBuilder.append(e.getField());
                    strBuilder.append(": ");
                    strBuilder.append(e.getDefaultMessage());
                    strBuilder.append("; ");
                });
        HashMap resultMap = new HashMap();
//        resultMap.put("message", strBuilder.toString());
//        return Map.of("message", strBuilder.toString());
//        return resultMap;
        return new BaseResponse(BizErrorCodeEnum.PARAM_ERROR, strBuilder.toString());
    }

    /**
     * 请求类型转换失败异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException exception,
            HttpServletRequest request) {
        String msg = request.getRequestURI() +
                ": " + "请求地址参数" + exception.getValue() + "错误";
        HashMap resultMap = new HashMap();
        resultMap.put("message", msg);
        return new BaseResponse(BizErrorCodeEnum.INVALID_METHOD, msg);
    }


    /**
     * FORBIDDEN
     */
    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public BaseResponse handleResponseStatusException(
            ResponseStatusException exception,
            HttpServletRequest request) {
        log.info(exceptionMsg + "type:{},URI:{}", ResponseStatusException.class.getName(), request.getRequestURI());
        String msg = request.getRequestURI() +
                ": " + exception.getReason();
        HashMap resultMap = new HashMap();
        resultMap.put("message", msg);
        return new BaseResponse(BizErrorCodeEnum.AUTH_FAILD, msg);
    }

    /**
     * 自定义异常的捕获
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(PmsServiceException.class)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse handleCustomException(PmsServiceException exception) {
        return BaseResponse.operationFailed(exception.getErrorMessage());
    }


//    /**
//     * 自定义异常的捕获
//     *
//     * @param exception
//     * @return
//     */
//    @ExceptionHandler(CustomException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Map handleCustomException(CustomException exception) {
//
//        return Map.of("message", exception.getMessage());
//    }


}
