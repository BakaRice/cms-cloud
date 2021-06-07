package com.ricemarch.cms.pms.mapper;

import com.ricemarch.cms.pms.entity.MakeWorkBook;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 标准作业书 Mapper 接口
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-21
 */
@Repository
public interface MakeWorkBookMapper extends BaseMapper<MakeWorkBook> {

    MakeWorkBook getByWorkNo(String workNo);
}
