package com.ricemarch.cmsgateway.util;

import com.alibaba.fastjson.JSONObject;
import com.ricemarch.cms.pms.common.facade.BaseResponse;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

//import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


/**
 * @author RiceMarch
 * @since 2021/4/11 23:02
 */
public class ResponseUtil {
//    /**
//     * 设置响应
//     *
//     * @param response    HttpServletResponse
//     * @param contentType content-type
//     * @param status      http状态码
//     * @param value       响应内容
//     * @throws IOException IOException
//     */
//    public static void responseWriter(HttpServletResponse response, String contentType,
//                                      int status, Object value) throws IOException {
//        response.setContentType(contentType);
//        response.setStatus(status);
//        response.getOutputStream().write(JSONObject.toJSONString(value).getBytes());
//    }

    /**
     * 设置webflux模型响应
     *
     * @param response    ServerHttpResponse
     * @param contentType content-type
     * @param status      http状态码
     * @param value       响应内容
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, String contentType,
                                                   HttpStatus status, Object value) {
        response.setStatusCode(status);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, contentType);
//        Result<?> result = Result.fail(status.value(), value.toString());
        BaseResponse<?> res = new BaseResponse<>(value);
//        DataBuffer dataBuffer = response.bufferFactory().wrap(JSONObject.toJSONString(result).getBytes());
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSONObject.toJSONString(res).getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }
}
