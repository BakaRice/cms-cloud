package com.ricemarch.cms.pms.service;

import com.ricemarch.cms.pms.bo.request.InstitutionAddRequest;
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
}
