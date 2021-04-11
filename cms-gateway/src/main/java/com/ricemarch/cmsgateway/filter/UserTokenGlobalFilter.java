package com.ricemarch.cmsgateway.filter;

import com.ricemarch.cmsgateway.util.ResponseUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 全局token拦截器
 *
 * @author RiceMarch
 * @since 2021/4/11 22:26
 */
@Component
public class UserTokenGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        log.info("[全局token拦截]");
        // 验证token是否有效
        ServerHttpResponse resp = exchange.getResponse();
        Object authorization = exchange.getAttribute("Authorization");
        List<String> token = exchange.getRequest().getHeaders().get("Authorization");
        if (!"/cms-pms-service/api/user/login".equals(exchange.getRequest().getPath())) {
            if (token == null || token.size() == 0 || StringUtils.isBlank(token.get(0))) {
                return unauthorized(resp, "没有携带Token信息！");
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }

    private Mono<Void> unauthorized(ServerHttpResponse resp, String msg) {
        return ResponseUtil.webFluxResponseWriter(resp, "application/json;charset=UTF-8", HttpStatus.UNAUTHORIZED, msg);
    }
}
