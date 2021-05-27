package com.ricemarch.cms.pms.mapper;

import com.ricemarch.cms.pms.entity.QualityPartClaim;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 零件质量级别和要求设定 Mapper 接口
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-21
 */
@Repository
public interface QualityPartClaimMapper extends BaseMapper<QualityPartClaim> {

    String getLevelByPartCode(String code);
}
