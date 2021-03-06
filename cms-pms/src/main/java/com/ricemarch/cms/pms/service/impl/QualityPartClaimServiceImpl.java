package com.ricemarch.cms.pms.service.impl;

import com.ricemarch.cms.pms.entity.QualityPartClaim;
import com.ricemarch.cms.pms.mapper.QualityPartClaimMapper;
import com.ricemarch.cms.pms.service.QualityPartClaimService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ricemarch.cms.pms.service.WarehousePartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 零件质量级别和要求设定 服务实现类
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-21
 */
@Service
public class QualityPartClaimServiceImpl extends ServiceImpl<QualityPartClaimMapper, QualityPartClaim> implements QualityPartClaimService {

    @Autowired
    QualityPartClaimMapper qualityPartClaimMapper;


    @Override
    public String getByPartCode(String code) {
        return qualityPartClaimMapper.getLevelByPartCode(code);
    }

    @Override
    public String getByPartName(String name) {
        return qualityPartClaimMapper.getByPartName(name);
    }
}
