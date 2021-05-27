package com.ricemarch.cms.pms.service;

import com.ricemarch.cms.pms.entity.MakeWorkBookSeq;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

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
}
