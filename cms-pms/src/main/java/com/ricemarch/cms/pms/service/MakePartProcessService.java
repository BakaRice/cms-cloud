package com.ricemarch.cms.pms.service;

import com.ricemarch.cms.pms.entity.MakePartProcess;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 零件加工记录表 服务类
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-21
 */
public interface MakePartProcessService extends IService<MakePartProcess> {

    MakePartProcess findByCode(String code);
}
