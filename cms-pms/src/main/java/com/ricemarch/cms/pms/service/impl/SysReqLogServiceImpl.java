package com.ricemarch.cms.pms.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ricemarch.cms.pms.common.component.EncryptComponent;
import com.ricemarch.cms.pms.common.component.MyToken;
import com.ricemarch.cms.pms.common.facade.BaseRequest;
import com.ricemarch.cms.pms.entity.SysReqLog;
import com.ricemarch.cms.pms.entity.User;
import com.ricemarch.cms.pms.mapper.SysReqLogMapper;
import com.ricemarch.cms.pms.service.SysReqLogService;
import com.ricemarch.cms.pms.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ricemarch
 * @since 2021-03-23
 */
@Service
@Slf4j
public class SysReqLogServiceImpl extends ServiceImpl<SysReqLogMapper, SysReqLog> implements SysReqLogService {

    @Autowired
    private EncryptComponent encryptComponent;

    @Autowired
    private UserService userService;

    @Override
    @Async("asyncServiceExecutor")
    public void save(ProceedingJoinPoint pjp, ServletRequestAttributes sra, long time, Object proceed) throws Exception {
        String logMsg = "请求日志切面:";
        assert sra != null;
        HttpServletRequest request = Optional.ofNullable(sra.getRequest())
                .orElseThrow((() -> new Exception("请求不能为空")));
//        log.info(logMsg + "request:{}", JSON.toJSONString(request));
        StringBuffer url_sb = Optional.ofNullable(request.getRequestURL()).orElse(new StringBuffer());
        String url = url_sb.toString();
        String uri = Optional.ofNullable(request.getRequestURI()).orElse("");
        String method = request.getMethod();

        //create sys req log object
        SysReqLog sysReqLog = new SysReqLog(url, uri, time, method, LocalDateTime.now(ZoneId.of("+08:00")));

        //set req resp
        if (pjp.getArgs().length != 0) {
//            BaseRequest arg = (BaseRequest) pjp.getArgs()[0];
//            log.debug(logMsg + "{}", JSON.toJSONString(arg));
//            sysReqLog.setReqparams(JSON.toJSONString(arg));
            Object[] args = pjp.getArgs();
            Stream<?> stream = ArrayUtils.isEmpty(args) ? Stream.empty() : Arrays.asList(args).stream();
            List<Object> logArgs = stream
                    .filter(arg -> (!(arg instanceof HttpServletRequest) && !(arg instanceof HttpServletResponse)))
                    .collect(Collectors.toList());
            //过滤后序列化无异常
            String req = JSON.toJSONString(logArgs);
            sysReqLog.setReqparams(req);
        }
        sysReqLog.setResparams(JSON.toJSONString(proceed));

        //set token
        String token = request.getHeader(MyToken.AUTHORIZATION);
        if (StringUtils.isNotBlank(token)) {
            //TODO company init cell id
            sysReqLog.setToken(token);
            MyToken myToken = encryptComponent.decryptToken(token);
            String phone = myToken.getPhone();
            QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
            queryWrapper.eq("phone", phone);
            queryWrapper.eq("is_delete", 1);
            Optional.ofNullable(userService.getOne(queryWrapper)).ifPresent(u -> {
                sysReqLog.setUsername(u.getName());
                sysReqLog.setUserId(u.getId());
                sysReqLog.setRequestid(u.getPhone());
            });
            sysReqLog.setToken(token);
        }
        log.info(logMsg + "sysReqLog:{}", JSON.toJSONString(sysReqLog));
        this.save(sysReqLog);

    }
}
