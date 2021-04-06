package com.ricemarch.cms.pms.service;

import com.ricemarch.cms.pms.entity.SysReqLog;
import com.baomidou.mybatisplus.extension.service.IService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ricemarch
 * @since 2021-03-23
 */
public interface SysReqLogService extends IService<SysReqLog> {

    void save(ProceedingJoinPoint pjp, ServletRequestAttributes sra, long time, Object proceed) throws Exception;

}
