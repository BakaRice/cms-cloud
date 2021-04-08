package com.ricemarch.cms.pms.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ricemarch.cms.pms.bo.request.admin.UserAddRequest;
import com.ricemarch.cms.pms.bo.request.UserCommonRequest;
import com.ricemarch.cms.pms.bo.request.UserUpdateRequest;
import com.ricemarch.cms.pms.common.expection.PmsServiceException;
import com.ricemarch.cms.pms.entity.User;
import com.ricemarch.cms.pms.mapper.*;
import com.ricemarch.cms.pms.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

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
    UserRoleMapper roleMapper;
    @Autowired
    ProfessionMapper professionMapper;
    @Autowired
    CellsMapper cellsMapper;
    @Autowired
    InstitutionMapper institutionMapper;

    @Autowired
    private PasswordEncoder encoder;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveUser(UserAddRequest request) {

        UserCommonRequest userCommonRequest = request.getUserCommonRequest();
        //查询手机号是否重复
        String phone = userCommonRequest.getPhone();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        queryWrapper.eq("is_delete", (short) 0);
        Optional.ofNullable(userMapper.selectOne(queryWrapper))
                .ifPresent(u -> {
                    throw new PmsServiceException("该手机号" + u.getPhone() + "已存在");
                });
        //查询角色是否存在
        Optional.ofNullable(roleMapper.selectById(request.getUserCommonRequest().getRoleId()))
                .orElseThrow(() -> new PmsServiceException("当前插入用户，用户角色不存在"));
        //查询工种是否存在
        Optional.ofNullable(professionMapper.selectById(request.getUserCommonRequest().getProfessionId()))
                .orElseThrow(() -> new PmsServiceException("当前插入用户，用户工种不存在"));

        //cellId存在时，查询cell是否存在
        if (null != request.getCellId() && 0L != request.getCellId()) {
            Optional.ofNullable(cellsMapper.selectById(request.getCellId()))
                    .orElseThrow(() -> new PmsServiceException("当前插入用户，所选班组不存在"));
        }
        //init Id 存在时，查询init是否存在
        if (null != request.getInstitutionId() && 0L != request.getInstitutionId()) {
            Optional.ofNullable(cellsMapper.selectById(request.getCellId()))
                    .orElseThrow(() -> new PmsServiceException("当前插入用户，所选组织不存在"));
        }
        User user = new User();
        BeanUtils.copyProperties(request.getUserCommonRequest(), user);
        //初始化密码为手机号 并进行加密
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

        //查询角色是否存在
        Optional.ofNullable(roleMapper.selectById(userUpdateRequest.getUserCommonRequest().getRoleId()))
                .orElseThrow(() -> new PmsServiceException("当前修改用户，用户角色不存在"));
        //查询工种是否存在
        Optional.ofNullable(professionMapper.selectById(userUpdateRequest.getUserCommonRequest().getProfessionId()))
                .orElseThrow(() -> new PmsServiceException("当前修改用户，用户工种不存在"));

        //cellId存在时，查询cell是否存在
        if (null != userUpdateRequest.getCellId() && 0L != userUpdateRequest.getCellId()) {
            Optional.ofNullable(cellsMapper.selectById(userUpdateRequest.getCellId()))
                    .orElseThrow(() -> new PmsServiceException("当前修改用户，所选班组不存在"));
        }
        //init Id 存在时，查询init是否存在
        if (null != userUpdateRequest.getInstitutionId() && 0L != userUpdateRequest.getInstitutionId()) {
            Optional.ofNullable(cellsMapper.selectById(userUpdateRequest.getCellId()))
                    .orElseThrow(() -> new PmsServiceException("当前修改用户，所选组织不存在"));
        }

        //此处不修改密码 修改密码由单独接口进行
        User updateUser = new User();
        BeanUtils.copyProperties(userCommonRequest, updateUser);
        updateUser.setId(selectByPhone.getId());
        //此处不能
        updateUser.setIsDelete(selectByPhone.getIsDelete());
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
    public Boolean removeByPhone(String phone, Long updateUserId) {
        User removeUser = selectByPhone(phone);
        //不存在当前用户时 直接返回
        if (null == removeUser) {
            return false;
        }

        removeUser.setIsDelete((short) 1);

        removeUser.setUpdateBy(updateUserId);
        removeUser.setUpdateTime(LocalDateTime.now());

        int i = userMapper.updateById(removeUser);
        return i == 1;
    }


}
