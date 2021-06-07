package com.ricemarch.cms.pms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ricemarch.cms.pms.controller.PartSeqDto;
import com.ricemarch.cms.pms.dto.make.SeqBookDto;
import com.ricemarch.cms.pms.entity.MakeWorkBookSeq;
import com.ricemarch.cms.pms.mapper.MakeWorkBookSeqMapper;
import com.ricemarch.cms.pms.service.MakeWorkBookSeqService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 工序 服务实现类
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-21
 */
@Service
public class MakeWorkBookSeqServiceImpl extends ServiceImpl<MakeWorkBookSeqMapper, MakeWorkBookSeq> implements MakeWorkBookSeqService {

    @Autowired
    MakeWorkBookSeqMapper makeWorkBookSeqMapper;

    @Override
    public MakeWorkBookSeq getByName(String sequenceName, String partName) {
        return makeWorkBookSeqMapper.getByName(sequenceName, partName);
    }

    @Override
    public MakeWorkBookSeq getInitSeqByPartCode(String code) {
        return makeWorkBookSeqMapper.getInitSeqByPartCode(code);
    }

    @Override
    public PageInfo<PartSeqDto> getPagePartWithMakeInfo(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<PartSeqDto> list = makeWorkBookSeqMapper.getPartWithMakeInfo();
        return new PageInfo<>(list);
    }

    @Override
    public List<MakeWorkBookSeq> getStepsByName(String name) {
        return makeWorkBookSeqMapper.getStepsByName(name);
    }

    @Override
    public List<SeqBookDto> getSeqBookDtos(String name) {
        return makeWorkBookSeqMapper.getSeqBookDtos(name);
    }
}
