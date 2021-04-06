package com.ricemarch.cms.pms.controller;

import com.alibaba.fastjson.JSON;
import com.ricemarch.cms.pms.bo.request.UserAddRequest;
import com.ricemarch.cms.pms.bo.request.UserCommonRequest;
import com.ricemarch.cms.pms.bo.request.UserUpdateRequest;
import com.ricemarch.cms.pms.common.facade.BaseRequest;
import com.ricemarch.cms.pms.common.facade.BaseResponse;
import com.ricemarch.cms.pms.entity.User;
import com.ricemarch.cms.pms.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
public class AdminController {

    String logClassMsg = "Admin操作";

    @Autowired
    UserService userService;

    /**
     * add a new user
     *
     * @param request
     * @return
     */
    @PostMapping("/user")
    public BaseResponse postUser(@RequestBody UserAddRequest request) {
        String method = "postInfo";
        log.info(logClassMsg + method + "request:{}", JSON.toJSONString(request));

        //TODO ENCODE PASSWORD
        Boolean isSuccess = userService.saveUser(request);
        if (isSuccess) {
            return BaseResponse.success("新增用户成功");
        } else {
            return BaseResponse.operationFailed("新增用户失败");
        }
    }

    @PutMapping("/user")
    public BaseResponse putUser(@RequestBody UserUpdateRequest request) {
        String method = "putUser";
        log.info(logClassMsg + method + "request:{}", JSON.toJSONString(request));

        Boolean isSuccess = userService.updateUser(request);
        if (isSuccess) {
            return BaseResponse.success("修改用户成功");
        } else {
            return BaseResponse.operationFailed("修改用户失败");
        }
    }

    @GetMapping("/user/{userId}")
    public BaseResponse getUser(@PathVariable("userId") String userId) {
        User user = userService.getById(userId);
        return new BaseResponse(user);
    }

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
    public BaseResponse postRole(@RequestBody BaseRequest request, @PathVariable("userId") String userId) {
        return new BaseResponse();
    }

    @PostMapping("/profession/{userId}")
    public BaseResponse postProfession(@RequestBody BaseRequest request, @PathVariable("userId") String userId) {
        return new BaseResponse();
    }

}
