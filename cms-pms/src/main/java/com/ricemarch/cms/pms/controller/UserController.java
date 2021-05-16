package com.ricemarch.cms.pms.controller;

import com.ricemarch.cms.pms.bo.request.UserLoginRequest;
import com.ricemarch.cms.pms.bo.response.UserLoginResponse;
import com.ricemarch.cms.pms.common.component.EncryptComponent;
import com.ricemarch.cms.pms.common.component.MyToken;
import com.ricemarch.cms.pms.common.expection.PmsServiceException;
import com.ricemarch.cms.pms.common.facade.BaseRequest;
import com.ricemarch.cms.pms.common.facade.BaseResponse;
import com.ricemarch.cms.pms.entity.User;
import com.ricemarch.cms.pms.entity.UserRole;
import com.ricemarch.cms.pms.service.UserRoleService;
import com.ricemarch.cms.pms.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

/**
 * default user controller
 * 打卡，自身信息修改，查看班次安排，查看考勤记录，请假，查看生产内容
 *
 * @author RiceMarch
 * @since 2021/3/22 22:07
 */
@Slf4j
@RestController
@RequestMapping("/api/pms/user")
public class UserController {

    String logClassMsg = "默认用户操作Controller";

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    EncryptComponent encrypt;

    @Autowired
    UserService userService;

    @Autowired
    UserRoleService userRoleService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public BaseResponse<UserLoginResponse> login(@Valid @RequestBody UserLoginRequest userLoginRequest, HttpServletResponse response) {
        User login;
        try {
            login = userService.selectByPhone(userLoginRequest.getPhone());
        } catch (PmsServiceException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名或密码错误");
        }


        User user = Optional.ofNullable(login)
                .filter(u -> encoder.matches(userLoginRequest.getPassword(), u.getPassword()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名或密码错误"));

        UserRole byId = userRoleService.getById(user.getRoleId());
        MyToken token = new MyToken(user.getPhone(), byId.getName());
        String auth = encrypt.encryptToken(token);
        //键值对 不要用硬编码 用常量 避免书写错误等情况
        response.setHeader(MyToken.AUTHORIZATION, auth);
        UserLoginResponse userLoginResponse = new UserLoginResponse(byId.getName(), user.getRoleId(), user.getName());
        return new BaseResponse<>(userLoginResponse);
    }


    // 考勤的结果 由 schedule 进行排定
    @GetMapping("/attendance")
    public BaseResponse getAttendance() {
        //从token中获取
        return new BaseResponse();
    }

    @PostMapping("/attendance")
    public BaseResponse postAttendance(@RequestBody BaseRequest request) {
        return new BaseResponse();
    }

    @GetMapping("/info")
    public BaseResponse getInfo() {
        //从token中获取
        return new BaseResponse();
    }

    @PostMapping("/info")
    public BaseResponse postInfo(@Valid @RequestBody BaseRequest request) {
        return BaseResponse.operationFailed();
    }

    @GetMapping("/schedule")
    public BaseResponse getSchedule() {
        //从token中获取
        return new BaseResponse();
    }

    //请假是一个动作 是不是应该包含在schedule中
    @PostMapping("/schedules")
    public BaseResponse postSchedule(@RequestBody BaseRequest request) {
        return new BaseResponse();
    }
}
