package com.ricemarch.cms.pms.service;

import com.ricemarch.cms.pms.bo.request.admin.UserAddRequest;
import com.ricemarch.cms.pms.bo.request.UserUpdateRequest;
import com.ricemarch.cms.pms.dto.Roster;
import com.ricemarch.cms.pms.dto.RosterOverview;
import com.ricemarch.cms.pms.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDate;
import java.util.List;

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

    /**
     * 通过userid和cellid 查找指定用户
     *
     * @param userId
     * @param cellId
     * @return
     */
    User selectByUserIdAndCellId(Long userId, Long cellId);

    /**
     * 通过userid和institutionId查找指定用户
     *
     * @param userId
     * @param institutionId
     * @return
     */
    User selectByUserIdAndInstitutionId(Long userId, Long institutionId);

    /**
     * 查询指定cellid对应的list user
     *
     * @param cellId
     * @return
     */
    List<User> selectByCellId(Long cellId);

    /**
     * 查询指定InstitutionId对应的list user
     *
     * @param InstitutionId
     * @return
     */
    List<User> selectByInstitutionId(Long InstitutionId);

    /**
     * 通过list of id 查询用户列表
     *
     * @return
     * @param userIdList
     */
    List<User> selectByUserIdList(List<Long> userIdList);

    List<Roster> selectCurrentDateRosterByInit(Long institutionId);

    List<Roster> selectCurrentDateRosterByCell(Long cellId);

    RosterOverview selectRosterOverview(Long institutionId, Long cellId);

    Roster getRosterByCurrDateAndUid(LocalDate currDate, Long userId);
}
