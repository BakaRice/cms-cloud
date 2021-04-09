package com.ricemarch.cms.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ricemarch.cms.pms.bo.request.admin.InstitutionAddRequest;
import com.ricemarch.cms.pms.bo.request.admin.InstitutionCommonRequest;
import com.ricemarch.cms.pms.bo.request.admin.InstitutionPageRequest;
import com.ricemarch.cms.pms.common.expection.PmsServiceException;
import com.ricemarch.cms.pms.entity.Cells;
import com.ricemarch.cms.pms.entity.Company;
import com.ricemarch.cms.pms.entity.Institution;
import com.ricemarch.cms.pms.mapper.CompanyMapper;
import com.ricemarch.cms.pms.mapper.InstitutionMapper;
import com.ricemarch.cms.pms.service.InstitutionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
public class InstitutionServiceImpl extends ServiceImpl<InstitutionMapper, Institution> implements InstitutionService {

    @Autowired
    InstitutionMapper institutionMapper;

    @Autowired
    CompanyMapper companyMapper;

    @Override
    public boolean saveInstitution(InstitutionAddRequest request) {
        InstitutionCommonRequest institutionCommonRequest = request.getInstitutionCommonRequest();

        Long companyId = institutionCommonRequest.getCompanyId();
        String name = institutionCommonRequest.getName();
        QueryWrapper<Company> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", companyId);
        //查询公司是否存在
        Optional.ofNullable(companyMapper.selectOne(queryWrapper))
                .orElseThrow(() -> new PmsServiceException("当前插入机构，所属公司不存在"));
        QueryWrapper<Institution> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("name", name);
        if (null != institutionMapper.selectOne(queryWrapper2)) {
            throw new PmsServiceException("当前插入机构，机构名称已存在");
        }

        Institution institution = new Institution();

        BeanUtils.copyProperties(institutionCommonRequest, institution);
        institution.setId(null);
        int insert = institutionMapper.insert(institution);

        return insert == 1;
    }

    @Override
    public PageInfo<Institution> listInstitution4Page(InstitutionPageRequest req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        List<Institution> institutionList = institutionMapper.selectList(Wrappers.emptyWrapper());
        PageInfo<Institution> institutionPageInfo = new PageInfo<>(institutionList);

        //...

        institutionPageInfo.setStartRow(req.getPageNum());
        institutionPageInfo.setPageSize(req.getPageSize());

        return institutionPageInfo;
    }
}
