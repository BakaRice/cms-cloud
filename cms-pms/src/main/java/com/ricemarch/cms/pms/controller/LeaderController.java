package com.ricemarch.cms.pms.controller;

import com.github.pagehelper.PageInfo;
import com.ricemarch.cms.pms.bo.request.admin.CellPageRequest;
import com.ricemarch.cms.pms.bo.response.UserCommonResponse;
import com.ricemarch.cms.pms.common.enums.BizErrorCodeEnum;
import com.ricemarch.cms.pms.common.expection.PmsServiceException;
import com.ricemarch.cms.pms.common.facade.BaseRequest;
import com.ricemarch.cms.pms.common.facade.BaseResponse;
import com.ricemarch.cms.pms.dto.CustomUser;
import com.ricemarch.cms.pms.entity.Cells;
import com.ricemarch.cms.pms.entity.User;
import com.ricemarch.cms.pms.service.*;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    public static final int CELL_LEADER_ROLE_ID = 6;
    public static final int INSTITUTION_LEADER_ROLE_ID = 5;


    @ApiOperation("查看本机构或部门下的所有员工的信息列表 PageInfo【TEST-2】")
    @GetMapping("/infos")
    public BaseResponse<PageInfo<CustomUser>> getInfoList() {
        //从token中获取
        Long cellId = getCellId();
        Long institutionId = getInstitutionId();
        Integer roleId = getRoleId();

        List<User> userList;

        if (CELL_LEADER_ROLE_ID == roleId) {
            Optional.ofNullable(cellId).orElseThrow(() -> new PmsServiceException("班组id不能为空"));
            userList = userService.selectByCellId(cellId);
        } else if (INSTITUTION_LEADER_ROLE_ID == roleId) {
            Optional.ofNullable(institutionId).orElseThrow(() -> new PmsServiceException("机构id不能为空"));
            userList = userService.selectByInstitutionId(institutionId);
        } else {
            throw new PmsServiceException("权限不足");
        }

        List<CustomUser> customUserList = new ArrayList<>();
        for (User user : userList) {
            CustomUser customUser = new CustomUser();
            BeanUtils.copyProperties(user, customUser);
            customUserList.add(customUser);
        }
        PageInfo<CustomUser> customUserPageInfo = new PageInfo<>(customUserList);
        return new BaseResponse<>(customUserPageInfo);
    }

    @ApiOperation("修改 user info list 进行操作")
    @PutMapping("/infos")
    public BaseResponse postInfoList(@RequestBody BaseRequest request) {
        return new BaseResponse();
    }

    @ApiOperation("修改 user info 通过userId 进行操作")
    @PutMapping("/info/{userId}")
    public BaseResponse postInfo(@RequestBody BaseRequest request, @PathVariable("userId") String userId) {
        return new BaseResponse();
    }

    @ApiOperation("根据userId查看leader所属机构内的用户信息【DONE】")
    @GetMapping("/info/{userId}")
    public BaseResponse<UserCommonResponse> getInfo(@PathVariable("userId") Long userId) {
        //从token中获取
        Integer roleId = getRoleId();
        Long cellId = getCellId();
        Long institutionId = getInstitutionId();
        Optional.ofNullable(roleId).orElseThrow(() -> new PmsServiceException("权限不足"));

        User user;

        if (CELL_LEADER_ROLE_ID == roleId) {
            Optional.ofNullable(cellId).orElseThrow(() -> new PmsServiceException("班组id不能为空"));
            user = userService.selectByUserIdAndCellId(userId, cellId);
        } else if (INSTITUTION_LEADER_ROLE_ID == roleId) {
            Optional.ofNullable(institutionId).orElseThrow(() -> new PmsServiceException("机构id不能为空"));
            user = userService.selectByUserIdAndInstitutionId(userId, institutionId);
        } else {
            throw new PmsServiceException("权限不足");
        }
        if (user == null) {
            return new BaseResponse<>(BizErrorCodeEnum.USER_DOES_NOT_EXISTS);
        }
        UserCommonResponse u = new UserCommonResponse();
        BeanUtils.copyProperties(user, u);
        return new BaseResponse<>(u);
    }

    @ApiOperation("查看本机构或部门下的所有员工的考勤列表 PageInfo 以天来计算")
    @GetMapping("/attendances/{date}")
    public BaseResponse getAttendance(@PathVariable("date") Date date) {
        //从token中获取
        return new BaseResponse();
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

    @ApiOperation("批量修改排班信息")
    @PostMapping("/schedules")
    public BaseResponse postScheduleList(@RequestBody BaseRequest request) {
        return new BaseResponse();
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
        Optional.ofNullable(roleId).orElseThrow(() -> new PmsServiceException("权限不足"));
        PageInfo<Cells> cellPageResponse = null;
        if (roleId == INSTITUTION_LEADER_ROLE_ID && institutionId != null) {
            cellPageResponse = cellService.listCellByInstitution4Page(request, institutionId);
        } else {
            throw new PmsServiceException("查询权限不足或参数错误");
        }
        return new BaseResponse<>(cellPageResponse);
    }

}
