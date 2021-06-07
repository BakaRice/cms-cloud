package com.ricemarch.cms.pms.mapper;

import com.ricemarch.cms.pms.entity.MakeWorkBookProcess;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 标准作业书流程 Mapper 接口
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-21
 */
@Repository
public interface MakeWorkBookProcessMapper extends BaseMapper<MakeWorkBookProcess> {

    List<MakeWorkBookProcess> getByBookId(Long id);
}
