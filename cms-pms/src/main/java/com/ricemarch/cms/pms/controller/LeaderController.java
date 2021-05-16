package com.ricemarch.cms.pms.controller;

import java.sql.Blob;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ricemarch.cms.pms.bo.request.SchedulesUserUpdateRequest;
import com.ricemarch.cms.pms.bo.request.UserCommonRequest;
import com.ricemarch.cms.pms.bo.request.UserUpdateRequest;
import com.ricemarch.cms.pms.bo.request.admin.CellPageRequest;
import com.ricemarch.cms.pms.bo.response.UserCommonResponse;
import com.ricemarch.cms.pms.common.enums.BizErrorCodeEnum;
import com.ricemarch.cms.pms.common.expection.PmsServiceException;
import com.ricemarch.cms.pms.common.facade.BaseRequest;
import com.ricemarch.cms.pms.common.facade.BaseResponse;
import com.ricemarch.cms.pms.dto.*;
import com.ricemarch.cms.pms.entity.*;
import com.ricemarch.cms.pms.service.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * leader controller
 * 修改员工信息 安排成员班次 查看成员考勤 生产安排 批假
 *
 * @author RiceMarch
 * @since 2021/3/22 22:39
 */
@Slf4j
@RestController
@RequestMapping("/api/pms/leader")
public class LeaderController extends BaseController {


    @Autowired
    UserService userService;
    @Autowired
    UserRoleService roleService;
    @Autowired
    ProfessionService professionService;
    @Autowired
    CellsService cellService;
    @Autowired
    InstitutionService institutionService;
    @Autowired
    SchedulingService schedulingService;
    @Autowired
    SchedulingTypeService schedulingTypeService;
    @Autowired
    AttendanceService attendanceService;

    public static final int CELL_LEADER_ROLE_ID = 6;
    public static final int INSTITUTION_LEADER_ROLE_ID = 5;

    public static final String CELL_ID_NULL = "班组id不能为空";
    public static final String INSTITUTION_ID_NULL = "机构id不能为空";
    public static final String PERMISSION_DENIED = "权限拒绝";


    @ApiOperation("查看本机构或部门下的所有员工的信息列表 PageInfo【TEST-3】")
    @GetMapping("/infos")
    public BaseResponse<PageInfo<CustomUser>> getInfoList(@RequestParam @NotNull int pageNum, @RequestParam int pageSize) {
        //从token中获取
        Long cellId = getCellId();
        Long institutionId = getInstitutionId();
        Integer roleId = getRoleId();

        List<User> userList;
        ////查询到的pageInfo -> userPageInfo
        PageInfo<User> userPageInfo = new PageInfo<>();
        if (CELL_LEADER_ROLE_ID == roleId && null != cellId) {
            PageHelper.startPage(pageNum, pageSize);
            userList = userService.selectByCellId(cellId);
            userPageInfo = new PageInfo<>(userList);
        } else if (INSTITUTION_LEADER_ROLE_ID == roleId && null != institutionId) {
            PageHelper.startPage(pageNum, pageSize);
            userList = userService.selectByInstitutionId(institutionId);
            userPageInfo = new PageInfo<>(userList);
        } else {
            if (null == institutionId) {
                throw new PmsServiceException(INSTITUTION_ID_NULL);
            }
            if (null == cellId) {
                throw new PmsServiceException(CELL_ID_NULL);
            }
            throw new PmsServiceException(PERMISSION_DENIED);
        }

        List<CustomUser> customUserList = new ArrayList<>();

        for (User user : userList) {
            CustomUser customUser = new CustomUser();
            BeanUtils.copyProperties(user, customUser);
            customUser.setAccountStateDesc(user.getAccountState() == 0 ? "已启用" : "未启用");
            customUser.setIdString(user.getId().toString());
            customUserList.add(customUser);
        }

        //要返回的pageInfo -> CustomUser
        PageInfo<CustomUser> customUserPageInfo = new PageInfo<>();
        BeanUtils.copyProperties(userPageInfo, customUserPageInfo);
        log.info(customUserPageInfo.toString());
        List<User> list = userPageInfo.getList();
        List<CustomUser> customUsers = new ArrayList<>();
        for (User user : list) {
            CustomUser customUser = new CustomUser();
            BeanUtils.copyProperties(user, customUser);
            customUsers.add(customUser);
        }
        customUserPageInfo.setList(customUserList);

        return new BaseResponse<>(customUserPageInfo);
    }

    @ApiOperation("查看本机构或部门下的所有员工的排班 PageInfo【TEST-0】")
    @GetMapping("/roster")
    public BaseResponse<PageInfo<Roster>> getCurrentDateRosterInfoList(@RequestParam @NotNull int pageNum, @RequestParam int pageSize) {
        //从token中获取
        Long cellId = getCellId();
        Long institutionId = getInstitutionId();
        Integer roleId = getRoleId();

        List<Roster> rosterList;
        if (CELL_LEADER_ROLE_ID == roleId && null != cellId) {
            PageHelper.startPage(pageNum, pageSize);
            rosterList = userService.selectCurrentDateRosterByCell(cellId);
        } else if (INSTITUTION_LEADER_ROLE_ID == roleId && null != institutionId) {
            PageHelper.startPage(pageNum, pageSize);
            rosterList = userService.selectCurrentDateRosterByInit(institutionId);
        } else {
            if (null == institutionId) {
                throw new PmsServiceException(INSTITUTION_ID_NULL);
            }
            if (null == cellId) {
                throw new PmsServiceException(CELL_ID_NULL);
            }
            throw new PmsServiceException(PERMISSION_DENIED);
        }
        PageInfo<Roster> rosterPageInfo = new PageInfo<>(rosterList);

        return new BaseResponse<>(rosterPageInfo);

    }

    @ApiOperation("【PUSH!】ROSTER-OVERIVEW【TEST-0】")
    @GetMapping("/roster-overview")
    public BaseResponse<RosterOverview> getRosterOverview() {
        //从token中获取
        Long cellId = getCellId();
        Long institutionId = getInstitutionId();
        Integer roleId = getRoleId();

        RosterOverview rosterOverview = new RosterOverview();
        if (CELL_LEADER_ROLE_ID == roleId && null != cellId) {

            rosterOverview = userService.selectRosterOverview(null, cellId);
        } else if (INSTITUTION_LEADER_ROLE_ID == roleId && null != institutionId) {

            rosterOverview = userService.selectRosterOverview(institutionId, null);
        } else {
            if (null == institutionId) {
                throw new PmsServiceException(INSTITUTION_ID_NULL);
            }
            if (null == cellId) {
                throw new PmsServiceException(CELL_ID_NULL);
            }
            throw new PmsServiceException(PERMISSION_DENIED);
        }
        return new BaseResponse<>(rosterOverview);
    }

    @ApiOperation("LEADER通过userId获取用户")
    @GetMapping("/user/{userId}")
    public BaseResponse<UserCommonResponse> getUser(@PathVariable("userId") Long userId) {
        User user = userService.getById(userId);
        if (user == null) {
            return new BaseResponse<>(BizErrorCodeEnum.USER_DOES_NOT_EXISTS);
        }
        UserCommonResponse u = new UserCommonResponse();
        BeanUtils.copyProperties(user, u);
        return new BaseResponse<>(u);
    }

    @ApiOperation("修改 user info list 进行操作")
    @PutMapping("/infos")
    public BaseResponse postInfoList(@RequestBody BaseRequest request) {
        return new BaseResponse();
    }

    @ApiOperation("修改 user info【TEST-0】 ")
    @PutMapping("/info")
    public BaseResponse<Void> postInfo(@RequestBody UserUpdateRequest request) {
        Long id = getUserId();
        Long userId = request.getUserCommonRequest().getId();
        Long cellId = getCellId();
        Integer roleId = getRoleId();
        Long institutionId = getInstitutionId();

        User user = userService.getById(userId);
//        if (CELL_LEADER_ROLE_ID == roleId && null != cellId) {
//            user = userService.selectByUserIdAndCellId(userId, cellId);
//
//        } else if (INSTITUTION_LEADER_ROLE_ID == roleId && null != institutionId) {
//            user = userService.selectByUserIdAndInstitutionId(userId, institutionId);
//        } else {
//            if (cellId == null) {
//                throw new PmsServiceException(CELL_ID_NULL);
//            }
//            if (institutionId == null) {
//                throw new PmsServiceException(INSTITUTION_ID_NULL);
//            }
//            throw new PmsServiceException(PERMISSION_DENIED);
//        }

        if (user == null) {
            throw new PmsServiceException("当前机构或班组下不存在该用户");
        }
        //不能修改的数据 cell inst role profession
        UserCommonRequest userCommonRequest = request.getUserCommonRequest();
        userCommonRequest.setUpdateBy(id);
        userCommonRequest.setUpdateTime(LocalDateTime.now());
        userCommonRequest.setInstitutionId(institutionId);
        userCommonRequest.setCellId(cellId);
        userCommonRequest.setRoleId(user.getRoleId());
        userCommonRequest.setProfessionId(user.getProfessionId());

        if (null == id) {
            throw new PmsServiceException("修改用户信息失败");
        }
        //以手机号为维度确认唯一性
        Boolean isSuccess = userService.updateUser(request);
        if (Boolean.TRUE.equals(isSuccess)) {
            return BaseResponse.success("修改用户成功", null);
        } else {
            return BaseResponse.operationFailed("修改用户失败", null);
        }
    }

    @ApiOperation("根据userId查看leader所属机构内的用户信息【DONE】")
    @GetMapping("/info/{userId}")
    public BaseResponse<UserCommonResponse> getInfo(@PathVariable("userId") Long userId) {
        //从token中获取
        Integer roleId = getRoleId();
        Long cellId = getCellId();
        Long institutionId = getInstitutionId();
        if (null == roleId) {
            throw new PmsServiceException(PERMISSION_DENIED);
        }
        User user;

        if (CELL_LEADER_ROLE_ID == roleId && null != cellId) {
            user = userService.selectByUserIdAndCellId(userId, cellId);
        } else if (INSTITUTION_LEADER_ROLE_ID == roleId && null != institutionId) {
            user = userService.selectByUserIdAndInstitutionId(userId, institutionId);
        } else {
            if (cellId == null) {
                throw new PmsServiceException(CELL_ID_NULL);
            }
            if (institutionId == null) {
                throw new PmsServiceException(INSTITUTION_ID_NULL);
            }
            throw new PmsServiceException(PERMISSION_DENIED);
        }
        if (user == null) {
            return new BaseResponse<>(BizErrorCodeEnum.USER_DOES_NOT_EXISTS);
        }
        UserCommonResponse u = new UserCommonResponse();
        BeanUtils.copyProperties(user, u);
        return new BaseResponse<>(u);
    }

    @ApiOperation("查看本机构或部门下的所有员工的考勤列表 PageInfo 以天来计算")
    @GetMapping("/attendances")
    public BaseResponse<PageInfo<AttendanceDto>> getAttendance(@RequestParam @NotNull int pageNum, @RequestParam int pageSize, @RequestParam LocalDate date) {
        //从token中获取
        Long cellId = getCellId();
        Long institutionId = getInstitutionId();
        Integer roleId = getRoleId();

        List<AttendanceDto> attendanceDtoList = new ArrayList<>();
        if (CELL_LEADER_ROLE_ID == roleId && null != cellId) {
            PageHelper.startPage(pageNum, pageSize);
            attendanceDtoList = attendanceService.selectDtoListByCellAndInitId(null, cellId,date);
        } else if (INSTITUTION_LEADER_ROLE_ID == roleId && null != institutionId) {
            PageHelper.startPage(pageNum, pageSize);
            attendanceDtoList = attendanceService.selectDtoListByCellAndInitId(institutionId, null,date);
        } else {
            if (null == institutionId) {
                throw new PmsServiceException(INSTITUTION_ID_NULL);
            }
            if (null == cellId) {
                throw new PmsServiceException(CELL_ID_NULL);
            }
            throw new PmsServiceException(PERMISSION_DENIED);
        }
        PageInfo<AttendanceDto> attendanceDtoPageInfo = new PageInfo<>(attendanceDtoList);
        return new BaseResponse<>(attendanceDtoPageInfo);
    }

    @ApiOperation("修改某人的某天的考勤")
    @PutMapping("/attendance/{date}/user/{userId}")
    public BaseResponse postAttendance(@RequestBody BaseRequest request, @PathVariable("date") Date date, @PathVariable("userId") String userId) {
        return new BaseResponse();
    }

    @ApiOperation("查看本机构或部门下的所有员工的排班")
    @GetMapping("/schedules/{userId}")
    public BaseResponse getSchedule(@PathVariable("userId") String userId) {
        //从token中获取进行鉴权
        return new BaseResponse();
    }

    @ApiOperation("批量修改排班信息【TEST-0】")
    @PostMapping("/schedules")
    public BaseResponse<Boolean> postScheduleList(@RequestBody SchedulesUserUpdateRequest request) {
        /*
         {
         "endTime": "2021-04-25",
         "schedulingTypeId": 0,
         "startTime": "2021-04-19",
         "userCommonRequestList": [
         {
         "id": 1383411424857026562
         },
         {
         "id": 1383411527856549889
         },
         {
         "id": 1383411568058953729
         },
         {
         "id": 1383411605547642882
         }
         ]
         }
         */
        Long createUserId = getUserId();
        LocalDate startTime = request.getStartTime();
        LocalDate endTime = request.getEndTime();
        if (startTime.compareTo(endTime) > 0) {
            throw new PmsServiceException("时间输入错误");
        }
        //获得排班类型 验证排班类型是否存在
        Integer schedulingTypeId = request.getSchedulingTypeId();
        SchedulingType type = schedulingTypeService.getById(schedulingTypeId);
        if (type == null) {
            throw new PmsServiceException("获取排班类型失败");
        }

        //校验用户列表 判断是否全部用户信息都是有效的 如果不是全部有效 则直接返回失败
        List<UserCommonRequest> userCommonRequestList = request.getUserCommonRequestList();
        List<Long> userIdList = new ArrayList<>();
        userCommonRequestList.forEach(u -> {
            userIdList.add(u.getId());
        });
        List<User> userList = userService.selectByUserIdList(userIdList);
        if (CollectionUtils.isEmpty(userList) || userList.size() != userCommonRequestList.size()) {
            return BaseResponse.operationFailed("用户列表存在有误信息", false);
        }
        //判断是否存在员工已经在当前时间段被排班
        //不在当前排班时间范围内的该员工列表的排班
        /**
         * 判断一个员工的排班是否冲突
         * 把他的所有现有排班取出来 把将要插入的排班插进去 看看会不会冲突
         */
        Map<Long, List<Roster>> userExistRosterListMap = schedulingService.getUserExistRosterListByUserIdList(userIdList);
        for (User user : userList) {
            Long id = user.getId();
            List<Roster> rosterList = userExistRosterListMap.get(id);
            Roster roster = new Roster();
            roster.setStartTime(startTime);
            roster.setEndTime(endTime);
            rosterList.add(roster);
            Collections.sort(rosterList);
            for (int i = 1; i < rosterList.size(); i++) {
                if (rosterList.get(i).getStartTime().compareTo(rosterList.get(i - 1).getEndTime()) <= 0) {
                    System.out.println("i" + rosterList.get(i).toString());
                    System.out.println("i-1" + rosterList.get(i - 1).toString());
                    return BaseResponse.operationFailed("用户列表存在该时间段已被排班的用户", false);
                }
            }
        }
        //组装排班列表
        List<Scheduling> schedulingList = new ArrayList<>();
        for (UserCommonRequest u : userCommonRequestList) {
            Scheduling scheduling = new Scheduling();
            scheduling.setUserId(u.getId())
                    .setScheduleTypeId(type.getId())
                    .setStartTime(startTime)
                    .setEndTime(endTime)
                    .setCreateBy(createUserId)
                    .setCreateTime(LocalDateTime.now())
                    .setUpdateBy(createUserId)
                    .setUpdateTime(LocalDateTime.now());
            schedulingList.add(scheduling);
        }
        //进行排班操作
        boolean isSuccess = schedulingService.saveBatch(schedulingList);
        //返回是否排班成功
        if (isSuccess) {
            return BaseResponse.success("批量修改排班信息成功", null);
        } else {
            return BaseResponse.operationFailed("批量修改排班信息失败", null);
        }
    }

    @ApiOperation("修改员工排班 请假的行为？ 由schedules_type 代表状态的转移 请假时 设置为 请假未批准 ， leader 可以修改为请假已批准")
    @PostMapping("/schedules/{userId}")
    public BaseResponse postSchedule(@RequestBody BaseRequest request) {
        return new BaseResponse();
    }

    @ApiOperation("查询本机构下cell 分页【TEST-2】")
    @GetMapping("/cell/{userId}")
    public BaseResponse<PageInfo<Cells>> postCellPages(@RequestParam int pageNum, @RequestParam int pageSize) {
        CellPageRequest request = new CellPageRequest();
        request.setPageNum(pageNum);
        request.setPageSize(pageSize);
        Long institutionId = getInstitutionId();
        Integer roleId = getRoleId();
        if (roleId == null) {
            throw new PmsServiceException(PERMISSION_DENIED);
        }
        PageInfo<Cells> cellPageResponse = null;
        if (roleId == INSTITUTION_LEADER_ROLE_ID && institutionId != null) {
            cellPageResponse = cellService.listCellByInstitution4Page(request, institutionId);
        } else {
            throw new PmsServiceException("查询权限不足或参数错误");
        }
        return new BaseResponse<>(cellPageResponse);
    }

}

