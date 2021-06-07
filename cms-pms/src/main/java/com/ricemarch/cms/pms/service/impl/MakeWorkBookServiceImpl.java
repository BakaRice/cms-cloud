package com.ricemarch.cms.pms.service.impl;

import com.ricemarch.cms.pms.common.expection.PmsServiceException;
import com.ricemarch.cms.pms.dto.make.WorkBookDto;
import com.ricemarch.cms.pms.entity.MakeWorkBook;
import com.ricemarch.cms.pms.entity.MakeWorkBookProcess;
import com.ricemarch.cms.pms.mapper.MakeWorkBookMapper;
import com.ricemarch.cms.pms.mapper.MakeWorkBookProcessMapper;
import com.ricemarch.cms.pms.service.MakeWorkBookProcessService;
import com.ricemarch.cms.pms.service.MakeWorkBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 标准作业书 服务实现类
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-21
 */
@Service
public class MakeWorkBookServiceImpl extends ServiceImpl<MakeWorkBookMapper, MakeWorkBook> implements MakeWorkBookService {

    @Autowired
    MakeWorkBookMapper makeWorkBookMapper;
    @Autowired
    MakeWorkBookProcessMapper makeWorkBookProcessMapper;

    @Override
    public WorkBookDto getWorkBookByWorkNo(String workNo) {
        MakeWorkBook makeWorkBook = makeWorkBookMapper.getByWorkNo(workNo);
        if (makeWorkBook == null) {
            throw new PmsServiceException("获取作业书失败！");
        }
        Long id = makeWorkBook.getId();
        List<MakeWorkBookProcess> processList = makeWorkBookProcessMapper.getByBookId(id);

        WorkBookDto workBookDto = new WorkBookDto();
        BeanUtils.copyProperties(makeWorkBook, workBookDto);
        workBookDto.setSequence(makeWorkBook.getWorkSequenceName());
        workBookDto.setProcess(processList);
        return workBookDto;
    }
}
