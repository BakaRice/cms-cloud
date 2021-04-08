package com.ricemarch.cms.pms.service;

import com.ricemarch.cms.pms.bo.request.CellAddRequest;
import com.ricemarch.cms.pms.entity.Cells;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ricemarch
 * @since 2021-03-22
 */
public interface CellsService extends IService<Cells> {

    boolean saveCell(CellAddRequest request);
}
