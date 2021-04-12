package com.ricemarch.cms.pms.service;

import com.github.pagehelper.PageInfo;
import com.ricemarch.cms.pms.bo.request.admin.CellAddRequest;
import com.ricemarch.cms.pms.bo.request.admin.CellPageRequest;
import com.ricemarch.cms.pms.bo.response.CellListResponse;
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

    PageInfo<Cells> listCell4Page(CellPageRequest request);

    PageInfo<Cells> listCellByInstitution4Page(CellPageRequest request, long institutionId);
}
