package com.ricemarch.cms.pms.config;

import com.ricemarch.cms.pms.interceptor.AdminInterceptor;
import com.ricemarch.cms.pms.interceptor.LeaderInterceptor;
import com.ricemarch.cms.pms.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author RiceMarch
 * @date 2021/3/7 17:25
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private AdminInterceptor adminInterceptor;
    @Autowired
    private LeaderInterceptor leaderInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/user/login");

        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/api/admin/**");

        registry.addInterceptor(leaderInterceptor)
                .addPathPatterns("/api/leader/**");
    }
}
