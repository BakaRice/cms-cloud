package com.ricemarch.cms.pms.mapper;

import com.ricemarch.cms.pms.entity.MakeWorkBookSeq;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 工序 Mapper 接口
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-21
 */
@Repository
public interface MakeWorkBookSeqMapper extends BaseMapper<MakeWorkBookSeq> {

    MakeWorkBookSeq getByName(@Param("sequenceName") String sequenceName, @Param("partName") String partName);

    MakeWorkBookSeq getInitSeqByPartCode(String code);
}
