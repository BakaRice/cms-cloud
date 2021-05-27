package com.ricemarch.cms.pms.service.impl;

import com.ricemarch.cms.pms.entity.MakeWorkBookSeq;
import com.ricemarch.cms.pms.mapper.MakeWorkBookSeqMapper;
import com.ricemarch.cms.pms.service.MakeWorkBookSeqService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return makeWorkBookSeqMapper.getByName( sequenceName, partName);
    }

    @Override
    public MakeWorkBookSeq getInitSeqByPartCode(String code) {
        return makeWorkBookSeqMapper.getInitSeqByPartCode(code);
    }
}
