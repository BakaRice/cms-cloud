package com.ricemarch.cms.pms.controller;

import com.ricemarch.cms.pms.common.facade.BaseRequest;
import com.ricemarch.cms.pms.common.facade.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * leader controller
 * 修改员工信息 安排成员班次 查看成员考勤 生产安排 批假
 *
 * @author RiceMarch
 * @since 2021/3/22 22:39
 */
@Slf4j
@RestController
@RequestMapping("/api/leader")
public class LeaderController {

    //查看本机构或部门下的所有员工的信息列表 PageInfo
    @GetMapping("/infos")
    public BaseResponse getInfoList() {
        //从token中获取
        return new BaseResponse();
    }

    //post user info list 进行操作
    @PutMapping("/infos")
    public BaseResponse postInfoList(@RequestBody BaseRequest request) {
        return new BaseResponse();
    }

    //post user info userId 进行操作
    @PutMapping("/info/{userId}")
    public BaseResponse postInfo(@RequestBody BaseRequest request, @PathVariable("userId") String userId) {
        return new BaseResponse();
    }

    @GetMapping("/info/{userId}")
    public BaseResponse getInfo(@PathVariable("userId") String userId) {
        //从token中获取
        return new BaseResponse();
    }

    //查看本机构或部门下的所有员工的考勤列表 PageInfo 以天来计算
    @GetMapping("/attendances/{date}")
    public BaseResponse getAttendance(@PathVariable("date") Date date) {
        //从token中获取
        return new BaseResponse();
    }

    //修改某人的某天的考勤
    @PutMapping("/attendance/{date}/user/{userId}")
    public BaseResponse postAttendance(@RequestBody BaseRequest request, @PathVariable("date") Date date, @PathVariable("userId") String userId) {
        return new BaseResponse();
    }

    //查看本机构或部门下的所有员工的排班
    @GetMapping("/schedules/{userId}")
    public BaseResponse getSchedule(@PathVariable("userId") String userId) {
        //从token中获取进行鉴权
        return new BaseResponse();
    }

    //批量修改排班信息
    @PostMapping("/schedules")
    public BaseResponse postScheduleList(@RequestBody BaseRequest request) {
        return new BaseResponse();
    }

    //修改员工排班 请假的行为？ 由schedules_type 代表状态的转移 请假时 设置为 请假未批准 ， leader 可以修改为请假已批准
    @PostMapping("/schedules/{userId}")
    public BaseResponse postSchedule(@RequestBody BaseRequest request) {
        return new BaseResponse();
    }


}
