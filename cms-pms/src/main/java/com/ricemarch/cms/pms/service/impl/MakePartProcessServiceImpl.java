package com.ricemarch.cms.pms.service.impl;

import com.ricemarch.cms.pms.entity.MakePartProcess;
import com.ricemarch.cms.pms.mapper.MakePartProcessMapper;
import com.ricemarch.cms.pms.service.MakePartProcessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 零件加工记录表 服务实现类
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-21
 */
@Service
public class MakePartProcessServiceImpl extends ServiceImpl<MakePartProcessMapper, MakePartProcess> implements MakePartProcessService {

    @Autowired
    MakePartProcessMapper makePartProcessMapper;

    @Override
    public MakePartProcess findByCode(String code) {
        return makePartProcessMapper.findByCode(code);
    }


}
