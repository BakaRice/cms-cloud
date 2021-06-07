package com.ricemarch.cms.pms.service;

import com.ricemarch.cms.pms.dto.make.WorkBookDto;
import com.ricemarch.cms.pms.entity.MakeWorkBook;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 标准作业书 服务类
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-21
 */
public interface MakeWorkBookService extends IService<MakeWorkBook> {

    WorkBookDto getWorkBookByWorkNo(String workNo);
}
