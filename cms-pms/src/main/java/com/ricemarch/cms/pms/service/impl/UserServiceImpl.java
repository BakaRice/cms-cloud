package com.ricemarch.cms.pms.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.ricemarch.cms.pms.bo.request.admin.UserAddRequest;
import com.ricemarch.cms.pms.bo.request.UserCommonRequest;
import com.ricemarch.cms.pms.bo.request.UserUpdateRequest;
import com.ricemarch.cms.pms.common.expection.PmsServiceException;
import com.ricemarch.cms.pms.dto.CustomUser;
import com.ricemarch.cms.pms.dto.Roster;
import com.ricemarch.cms.pms.dto.RosterOverview;
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
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
        user.setCompanyId(0L);
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

    @Override
    public User selectByUserIdAndCellId(Long userId, Long cellId) {
        if (userId == null || cellId == null) {
            throw new PmsServiceException("通过指定机构id和用户id查询用户失败");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cell_id", cellId);
        queryWrapper.eq("id", userId);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    @Override
    public User selectByUserIdAndInstitutionId(Long userId, Long institutionId) {
        if (userId == null || institutionId == null) {
            throw new PmsServiceException("通过指定机构id和用户id查询用户失败");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("institution_id", institutionId);
        queryWrapper.eq("id", userId);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    @Override
    public List<User> selectByCellId(Long cellId) {
        if (cellId == null) {
            throw new PmsServiceException("通过指定班组id查询用户失败");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cell_id", cellId);
        queryWrapper.eq("is_delete", 0);
        queryWrapper.orderByDesc("update_time");
        List<User> userList = userMapper.selectList(queryWrapper);
        return userList;
    }

    @Override
    public List<User> selectByInstitutionId(Long institutionId) {
        if (institutionId == null) {
            throw new PmsServiceException("通过指定机构id查询用户失败");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("institution_id", institutionId);
        queryWrapper.eq("is_delete", 0);
        queryWrapper.orderByDesc("update_time");
//        PageHelper.startPage(1, 10);
        List<User> userList = userMapper.selectList(queryWrapper);
        PageInfo userPageInfo = new PageInfo<>(userList);
        log.info(userPageInfo.toString());
        return userList;
    }

    @Override
    public List<User> selectByUserIdList(List<Long> userIdList) {
        if (CollectionUtils.isEmpty(userIdList)) {
            return Lists.newArrayList();
        }
        return userMapper.selectIdList(userIdList);
    }

    @Override
    public List<Roster> selectCurrentDateRosterByInit(Long institutionId) {
        if (institutionId == null) {
            throw new PmsServiceException("通过指定机构id查询用户失败");
        }
        return userMapper.selectRoster(null, institutionId);
    }

    @Override
    public List<Roster> selectCurrentDateRosterByCell(Long cellId) {
        if (cellId == null) {
            throw new PmsServiceException("通过指定班组id查询用户失败");
        }
        return userMapper.selectRoster(cellId, null);
    }

    @Override
    public RosterOverview selectRosterOverview(Long institutionId, Long cellId) {
        if (cellId == null && institutionId == null) {
            throw new PmsServiceException("通过指定班组id查询用户失败");
        }
//        if (institutionId == null) {
//            throw new PmsServiceException("通过指定机构id查询用户失败");
//        }
        RosterOverview rosterOverview = new RosterOverview();
        rosterOverview
                .setUserCount(userMapper.selectRosterUserCount(cellId, institutionId))
                .setUnDealUserCount(userMapper.selectRosterUnDealUserCount(cellId, institutionId))
                .setDayUserCount(userMapper.selectRosterDayUserCount(cellId, institutionId))
                .setNightUserCount(userMapper.selectRosterNightUserCount(cellId, institutionId));
        return rosterOverview;
    }

    @Override
    public Roster getRosterByCurrDateAndUid(LocalDate currDate, Long userId) {
        return userMapper.getRosterByCurrDateAndUid(currDate,userId);
    }

    @Override
    public List<CustomUser> selectFindByCellId(Long cellId, String find) {
        return userMapper.selectFindByCellAndInstitutionId(cellId,null,find);
    }

    @Override
    public List<CustomUser> selectFindByInstitutionId(Long institutionId, String find) {
        return userMapper.selectFindByCellAndInstitutionId(null,institutionId,find);
    }


}
