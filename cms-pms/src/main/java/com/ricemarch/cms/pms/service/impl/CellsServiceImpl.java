package com.ricemarch.cms.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ricemarch.cms.pms.bo.request.CellAddRequest;
import com.ricemarch.cms.pms.bo.request.CellCommonRequest;
import com.ricemarch.cms.pms.common.expection.PmsServiceException;
import com.ricemarch.cms.pms.entity.Cells;
import com.ricemarch.cms.pms.entity.Institution;
import com.ricemarch.cms.pms.mapper.CellsMapper;
import com.ricemarch.cms.pms.mapper.InstitutionMapper;
import com.ricemarch.cms.pms.service.CellsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ricemarch
 * @since 2021-03-22
 */
@Service
public class CellsServiceImpl extends ServiceImpl<CellsMapper, Cells> implements CellsService {

    @Autowired
    CellsMapper cellsMapper;

    @Autowired
    InstitutionMapper institutionMapper;

    @Override
    public boolean saveCell(CellAddRequest request) {
        CellCommonRequest cellCommonRequest = request.getCellCommonRequest();

        Long institutionId = cellCommonRequest.getInstitutionId();
        String name = cellCommonRequest.getName();
        QueryWrapper<Institution> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", institutionId);
        //查询机构是否存在
        Optional.ofNullable(institutionMapper.selectOne(queryWrapper))
                .orElseThrow(() -> new PmsServiceException("当前插入班组，所属机构不存在"));
        QueryWrapper<Cells> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("name", name);
        if (null != cellsMapper.selectOne(queryWrapper2)) {
            throw new PmsServiceException("当前插入班组，班组名称已存在");
        }
        Cells cell = new Cells();

        BeanUtils.copyProperties(cellCommonRequest, cell);
        cell.setId(null);
        int insert = cellsMapper.insert(cell);

        return insert == 1;
    }
}
