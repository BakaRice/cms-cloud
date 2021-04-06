package com.ricemarch.cms.pms.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ricemarch.cms.pms.bo.request.*;
import com.ricemarch.cms.pms.common.expection.PmsServiceException;
import com.ricemarch.cms.pms.common.facade.BaseRequest;
import com.ricemarch.cms.pms.common.facade.BaseResponse;
import com.ricemarch.cms.pms.entity.Profession;
import com.ricemarch.cms.pms.entity.User;
import com.ricemarch.cms.pms.entity.UserRole;
import com.ricemarch.cms.pms.service.ProfessionService;
import com.ricemarch.cms.pms.service.UserRoleService;
import com.ricemarch.cms.pms.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * admin controller
 * <p>
 * 新建员工 修改员工role profession
 *
 * @author RiceMarch
 * @since 2021/3/22 22:40
 */
@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminController extends BaseController {

    String logClassMsg = "Admin操作";

    @Autowired
    UserService userService;
    @Autowired
    UserRoleService roleService;
    @Autowired
    ProfessionService professionService;


    @ApiOperation("新增用户")
    @PostMapping("/user")
    public BaseResponse postUser(@Valid @RequestBody UserAddRequest request) {
        String method = "postInfo";
        String createUser = super.getCustomer().getName();
        Integer roleId = super.getCustomer().getRoleId();

        String updateUser = super.getCustomer().getName();
        request.getUserCommonRequest().setCreateBy(createUser);
        request.getUserCommonRequest().setUpdateBy(updateUser);
        log.info(logClassMsg + method + "request:{}", JSON.toJSONString(request));
        Boolean isSuccess = userService.saveUser(request);
        if (isSuccess) {
            return BaseResponse.success("新增用户成功");
        } else {
            return BaseResponse.operationFailed("新增用户失败");
        }
    }

    @ApiOperation("修改用户")
    @PutMapping("/user")
    public BaseResponse putUser(@Valid @RequestBody UserUpdateRequest request) {
        String method = "putUser";
        log.info(logClassMsg + method + "request:{}", JSON.toJSONString(request));

        Boolean isSuccess = userService.updateUser(request);
        if (isSuccess) {
            return BaseResponse.success("修改用户成功");
        } else {
            return BaseResponse.operationFailed("修改用户失败");
        }
    }

    @ApiOperation("通过userId获取用户")
    @GetMapping("/user/{userId}")
    public BaseResponse getUser(@PathVariable("userId") String userId) {
        User user = userService.getById(userId);
        return new BaseResponse(user);
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/user")
    public BaseResponse delUser(@RequestBody UserCommonRequest request) {
        String phone = request.getPhone();
        Boolean isSuccess = userService.removeByPhone(phone);
        if (isSuccess) {
            return BaseResponse.success("删除用户成功");
        } else {
            return BaseResponse.operationFailed("删除用户失败");
        }
    }

    //----以下两项都可以在info中被查看 但仅admin能进行修改

    @PostMapping("/role/{userId}")
    public BaseResponse putRole(@RequestBody BaseRequest request, @PathVariable("userId") String userId) {
        return new BaseResponse();
    }

    @ApiOperation("新增用户角色")
    @PostMapping("/role")
    public BaseResponse postRole(@Valid @RequestBody AdminRoleReq roleReq) {
        roleReq.setCreateBy(super.getCustomer().getName());
        roleReq.setUpdateBy(super.getCustomer().getName());
        UserRole userRole = new UserRole();
        BeanUtils.copyProperties(roleReq, userRole);
        try {
            QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name", userRole.getName());
            UserRole one = roleService.getOne(queryWrapper);
            if (null != one) {
                throw new PmsServiceException("当前角色名称已存在");
            }
            boolean save = roleService.save(userRole);
        } catch (Exception e) {
            throw new PmsServiceException("新增用户角色失败");
        }

        return new BaseResponse();
    }

    @ApiOperation("新增用户工种")
    @PostMapping("/profession")
    public BaseResponse postProfession(@Valid @RequestBody AdminProfessionReq professionReq) {
        Profession profession = new Profession();
        BeanUtils.copyProperties(professionReq, profession);
        try {
            QueryWrapper<Profession> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("code", profession.getCode());
            Profession one = professionService.getOne(queryWrapper);
            if (null != one) {
                throw new PmsServiceException("当前工种CODE已存在");
            }
            boolean save = professionService.save(profession);
        } catch (Exception e) {
            throw new PmsServiceException("新增用户工种失败");
        }
        return new BaseResponse();
    }

    @PostMapping("/profession/{userId}")
    public BaseResponse postProfession(@RequestBody BaseRequest request, @PathVariable("userId") String userId) {
        return new BaseResponse();
    }

}
