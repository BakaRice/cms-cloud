package com.ricemarch.cms.pms.aspect;

import com.ricemarch.cms.pms.service.SysReqLogService;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author RiceMarch
 * @date 2021/3/5 21:26
 */
@Aspect
@Component
@Slf4j
public class ReqAspect {

    @Autowired
    SysReqLogService sysReqLogService;


    @Pointcut("execution(* com.ricemarch.cms.pms.controller..*.*(..))")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object beforeAdvice(ProceedingJoinPoint pjp) throws Throwable {
        log.info("{}", pjp.getTarget().getClass().getName());
        long startTime = System.currentTimeMillis();
        //从requestContextHolder中获取 request attributes
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //将给定的RequestAttributes绑定到当前线程
        RequestContextHolder.setRequestAttributes(sra, true);
        Object proceed = pjp.proceed();
        long time = System.currentTimeMillis() - startTime;
        sysReqLogService.save(pjp, sra, time, proceed);
        return proceed;

    }
}
