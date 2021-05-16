package com.ricemarch.cms.pms.controller;

import com.ricemarch.cms.pms.bo.request.UserLoginRequest;
import com.ricemarch.cms.pms.bo.response.UserLoginResponse;
import com.ricemarch.cms.pms.common.component.EncryptComponent;
import com.ricemarch.cms.pms.common.component.MyToken;
import com.ricemarch.cms.pms.common.expection.PmsServiceException;
import com.ricemarch.cms.pms.common.facade.BaseRequest;
import com.ricemarch.cms.pms.common.facade.BaseResponse;
import com.ricemarch.cms.pms.dto.Roster;
import com.ricemarch.cms.pms.entity.Attendance;
import com.ricemarch.cms.pms.entity.User;
import com.ricemarch.cms.pms.entity.UserRole;
import com.ricemarch.cms.pms.service.AttendanceService;
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
import java.time.LocalDate;
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
public class UserController extends BaseController {

    String logClassMsg = "默认用户操作Controller";

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    EncryptComponent encrypt;

    @Autowired
    UserService userService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    AttendanceService attendanceService;

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
        return new BaseResponse();
    }

    @PostMapping("/attendance")
    public BaseResponse postAttendance(@RequestBody BaseRequest request) {
        //从token中获取
        Long cellId = getCellId();
        Long institutionId = getInstitutionId();
        Integer roleId = getRoleId();
        Long userId = getUserId();
//        考勤状态(0正常，1迟到，2早退，3旷工，4请假，5出差）

        //获取当前日期
        LocalDate currDate = LocalDate.now();
        //获取当前排班信息 若无排班信息 失败
        Roster roster = userService.getRosterByCurrDateAndUid(currDate,userId);
        if (roster==null){
            throw new PmsServiceException("没有排班信息");
        }
        //查看有无当前日期的打卡记录
        Attendance currDateAttendance = attendanceService.getByCurrDateAndUid(currDate,userId);
        //若有排班类型 无打卡记录 当前时间和上班班时间对比
        if (currDateAttendance==null){

        }else {
            //若有排班类型 有打卡记录 当前时间和下班班时间对比
        }


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
