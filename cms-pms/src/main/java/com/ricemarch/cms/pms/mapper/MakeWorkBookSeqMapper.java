package com.ricemarch.cms.pms.mapper;

import com.ricemarch.cms.pms.common.facade.BaseResponse;
import com.ricemarch.cms.pms.controller.PartSeqDto;
import com.ricemarch.cms.pms.dto.make.SeqBookDto;
import com.ricemarch.cms.pms.entity.MakeWorkBookSeq;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    List<PartSeqDto> getPartWithMakeInfo();

    List<MakeWorkBookSeq> getStepsByName(@Param("name") String name);

    List<SeqBookDto> getSeqBookDtos(@Param("name") String name);
}
