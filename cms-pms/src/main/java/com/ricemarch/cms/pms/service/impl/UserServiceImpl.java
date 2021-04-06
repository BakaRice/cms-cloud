package com.ricemarch.cms.pms.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ricemarch.cms.pms.bo.request.UserAddRequest;
import com.ricemarch.cms.pms.bo.request.UserCommonRequest;
import com.ricemarch.cms.pms.bo.request.UserUpdateRequest;
import com.ricemarch.cms.pms.common.component.EncryptComponent;
import com.ricemarch.cms.pms.common.expection.PmsServiceException;
import com.ricemarch.cms.pms.entity.User;
import com.ricemarch.cms.pms.mapper.UserMapper;
import com.ricemarch.cms.pms.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.TooManyListenersException;

/**
 * <p>
 * 员工 服务实现类
 * </p>
 *
 * @author ricemarch
 * @since 2021-03-22
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    String logMsg = "员工 服务实现类";

    @Autowired
    UserMapper userMapper;

    @Autowired
    private PasswordEncoder encoder;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveUser(UserAddRequest request) {

        UserCommonRequest userCommonRequest = request.getUserCommonRequest();
        String phone = userCommonRequest.getPhone();
        User selectByPhone = selectByPhone(phone);
        if (null != selectByPhone) {
            throw new PmsServiceException("该手机号" + phone + "已存在");
        }
        //TODO 还需要对现有用户进行查询 确认没有重复手机号等
        User user = new User();
        BeanUtils.copyProperties(request.getUserCommonRequest(), user);
        user.setPassword(encoder.encode(user.getPhone()));
        user.setId(null);

        log.info(logMsg + "user:{}", JSON.toJSONString(user));
        int insert = userMapper.insert(user);
        return insert == 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateUser(UserUpdateRequest userUpdateRequest) {
        UserCommonRequest userCommonRequest = userUpdateRequest.getUserCommonRequest();
        String phone = userCommonRequest.getPhone();
        User selectByPhone = selectByPhone(phone);

        //TODO PASSWORD

        User updateUser = new User();
        BeanUtils.copyProperties(userCommonRequest, updateUser);
        updateUser.setId(selectByPhone.getId());
        updateUser.setUpdateBy("//TODO");
        updateUser.setUpdateTime(LocalDateTime.now());

        int i = userMapper.updateById(updateUser);
        return i == 1;
    }

    @Override
    public User selectByPhone(String phone) {
        //132 5161 2723
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        queryWrapper.eq("is_delete", (short) 0);

        User user = Optional
                .ofNullable(userMapper.selectOne(queryWrapper))
                .orElseThrow((() -> new PmsServiceException("该手机号" + phone + ",用户不存在")));

        return user;
    }

    @Override
    public Boolean removeByPhone(String phone) {
        User removeUser = selectByPhone(phone);

        removeUser.setIsDelete((short) 1);

        removeUser.setUpdateBy("//TODO");
        removeUser.setUpdateTime(LocalDateTime.now());

        int i = userMapper.updateById(removeUser);
        return i == 1;
    }


}
