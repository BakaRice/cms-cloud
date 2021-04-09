package com.ricemarch.cms.pms.service;

import com.github.pagehelper.PageInfo;
import com.ricemarch.cms.pms.bo.request.admin.InstitutionAddRequest;
import com.ricemarch.cms.pms.bo.request.admin.InstitutionPageRequest;
import com.ricemarch.cms.pms.entity.Institution;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ricemarch
 * @since 2021-03-22
 */
public interface InstitutionService extends IService<Institution> {

    boolean saveInstitution(InstitutionAddRequest request);

    PageInfo<Institution> listInstitution4Page(InstitutionPageRequest request);
}
