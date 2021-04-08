package com.ricemarch.cms.pms.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ricemarch.cms.pms.bo.request.*;
import com.ricemarch.cms.pms.bo.response.UserCommonResponse;
import com.ricemarch.cms.pms.common.enums.BizErrorCodeEnum;
import com.ricemarch.cms.pms.common.expection.PmsServiceException;
import com.ricemarch.cms.pms.common.facade.BaseResponse;
import com.ricemarch.cms.pms.entity.Profession;
import com.ricemarch.cms.pms.entity.User;
import com.ricemarch.cms.pms.entity.UserRole;
import com.ricemarch.cms.pms.service.*;
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
    @Autowired
    CellsService cellService;
    @Autowired
    InstitutionService institutionService;


    @ApiOperation("新增cell")
    @PostMapping("/cell")
    public BaseResponse postCell(@Valid @RequestBody CellAddRequest request) {
        String method = "postInfo";
        Long createUserId = getUserId();
        request.getCellCommonRequest().setCreateBy(createUserId);
        request.getCellCommonRequest().setUpdateBy(createUserId);
        log.info(logClassMsg + "新增cell,request:{}", JSON.toJSONString(request));
        boolean isSuccess = cellService.saveCell(request);
        if (isSuccess) {
            return BaseResponse.success("新增班组成功");
        } else {
            return BaseResponse.operationFailed("新增班组失败");
        }
    }


    @ApiOperation("新增institution")
    @PostMapping("/institution")
    public BaseResponse postInit(@Valid @RequestBody InstitutionAddRequest request) {
        String method = "postInfo";
        Long createUserId = getUserId();
        request.getInstitutionCommonRequest().setCreateBy(createUserId);
        request.getInstitutionCommonRequest().setUpdateBy(createUserId);

        log.info(logClassMsg + "新增institution,request:{}", JSON.toJSONString(request));
        boolean isSuccess = institutionService.saveInstitution(request);
        if (isSuccess) {
            return BaseResponse.success("新增机构成功");
        } else {
            return BaseResponse.operationFailed("新增机构失败");
        }
    }

    @ApiOperation("新增用户")
    @PostMapping("/user")
    public BaseResponse postUser(@Valid @RequestBody UserAddRequest request) {
        String method = "postInfo";
        Long createUserId = getUserId();

        request.getUserCommonRequest().setCreateBy(createUserId);
        request.getUserCommonRequest().setUpdateBy(createUserId);
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
        Long id = getUserId();
        if (null == id) {
            throw new PmsServiceException("修改用户信息失败");
        }
        //以手机号为维度确认唯一性
        Boolean isSuccess = userService.updateUser(request);
        if (isSuccess) {
            return BaseResponse.success("修改用户成功");
        } else {
            return BaseResponse.operationFailed("修改用户失败");
        }
    }

    @ApiOperation("通过userId获取用户")
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

    @ApiOperation("删除用户")
    @DeleteMapping("/user")
    public BaseResponse delUser(@RequestBody UserCommonRequest request) {
        String phone = request.getPhone();
        Long updateUserId = getUserId();
        Boolean isSuccess = userService.removeByPhone(phone, updateUserId);
        if (isSuccess) {
            return BaseResponse.success("删除用户成功");
        } else {
            return BaseResponse.operationFailed("删除用户失败");
        }
    }

    //----以下两项都可以在info中被查看 但仅admin能进行修改

    @ApiOperation("新增用户角色")
    @PostMapping("/role")
    public BaseResponse postRole(@Valid @RequestBody AdminRoleReq roleReq) {
        Long id = getUserId();
        if (null == id) {
            throw new PmsServiceException("新增用户角色失败");
        }
        roleReq.setCreateBy(super.getCustomer().getId());
        roleReq.setUpdateBy(super.getCustomer().getId());
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

}
