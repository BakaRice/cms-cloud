package com.ricemarch.cms.pms.service;

import com.ricemarch.cms.pms.bo.request.UserAddRequest;
import com.ricemarch.cms.pms.bo.request.UserUpdateRequest;
import com.ricemarch.cms.pms.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 员工 服务类
 * </p>
 *
 * @author ricemarch
 * @since 2021-03-22
 */
public interface UserService extends IService<User> {

    /**
     * 保存新用户 返回userid
     *
     * @param request
     * @return
     */
    Boolean saveUser(UserAddRequest request);

    /**
     * 修改用户
     *
     * @return
     */
    Boolean updateUser(UserUpdateRequest userUpdateRequest);

    /**
     * 通过手机号查找用户
     *
     * @param phone
     * @return
     */
    User selectByPhone(String phone);

    /**
     * 通过手机号逻辑删除用户 is_delete 置为 true
     *
     * @param phone
     * @param updateUserId
     * @return
     */
    Boolean removeByPhone(String phone, Long updateUserId);
}
