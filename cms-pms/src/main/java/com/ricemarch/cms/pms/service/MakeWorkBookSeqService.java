package com.ricemarch.cms.pms.service;

import com.github.pagehelper.PageInfo;
import com.ricemarch.cms.pms.controller.PartSeqDto;
import com.ricemarch.cms.pms.dto.make.SeqBookDto;
import com.ricemarch.cms.pms.entity.MakeWorkBookSeq;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 工序 服务类
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-21
 */
public interface MakeWorkBookSeqService extends IService<MakeWorkBookSeq> {

    MakeWorkBookSeq getByName( String sequenceName, String partName);

    MakeWorkBookSeq getInitSeqByPartCode(String code);

    PageInfo<PartSeqDto> getPagePartWithMakeInfo(int pageNum, int pageSize);

    List<MakeWorkBookSeq> getStepsByName(String name);

    List<SeqBookDto> getSeqBookDtos(String name);
}
