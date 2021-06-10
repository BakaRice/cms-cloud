package com.ricemarch.cms.pms.service;

import java.time.LocalDateTime;
import java.util.List;

import com.ricemarch.cms.pms.common.component.WorkInitUtils;
import com.ricemarch.cms.pms.common.expection.PmsServiceException;
import com.ricemarch.cms.pms.common.facade.BaseResponse;
import com.ricemarch.cms.pms.dto.make.WorkBookDto;
import com.ricemarch.cms.pms.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * 制造 事务 服务
 *
 * @author RiceMarch
 * @date 2021/5/21 13:13
 */
@Service
@Slf4j
public class MakeTxService {

    @Autowired
    MakePartProcessService processService;

    @Autowired
    MakeUserRecordService userRecordService;

    @Autowired
    MakeWorkBookSeqService seqService;

    @Autowired
    WarehousePartService warehousePartService;

    @Autowired
    MakeWorkBookService workBookService;

    @Autowired
    MakeWorkBookProcessService workBookProcessService;

    @Autowired
    MakeWorkBookSeqService workBookSeqService;

    @Autowired
    WorkInitUtils workInitUtils;

    @Transactional(rollbackFor = Exception.class)
    public Boolean initProcess(String code, Long userId, String userName) {
        LocalDateTime now = LocalDateTime.now();
//        判断零件是否已经完成加工初始化
        MakePartProcess makePartProcess = new MakePartProcess();
        makePartProcess = processService.findByCode(code);
        if (makePartProcess != null) {
            throw new PmsServiceException("当前编号零件已经完成加工初始化");
        }
//        新建加工流程 => make_part_process
        //获取检查级别
        //TODO
        String workId = WorkInitUtils.initWorkId(code);
        makePartProcess = new MakePartProcess();
        makePartProcess.setWorkId(workId)
                .setStatus(0) //0毛胚
                .setCodeId(code) //零件编号 code
                .setStartTime(now)
                .setFlowCode(workInitUtils.initFlowCode(code));//需要读取对应零件的工序进行初始化
        processService.save(makePartProcess);
//        记录加工user信息到 make_user_record
        MakeUserRecord makeUserRecord = new MakeUserRecord();
        makeUserRecord.setUserId(userId);
        makeUserRecord.setPartCode(code);
        makeUserRecord.setTime(now);

        MakeWorkBookSeq seq = seqService.getInitSeqByPartCode(code);
        if (seq == null) {
            throw new PmsServiceException("加工初始化失败，当前零件未初始化工序信息");
        }
        makeUserRecord.setSeqId(seq.getId());
        makeUserRecord.setUserName(userName);
        userRecordService.save(makeUserRecord);
        return true;
    }


    @Transactional(rollbackFor = Exception.class)
    public BaseResponse<Boolean> initWorkBook(WorkBookDto workBookDto) {
        MakeWorkBook makeWorkBookI = new MakeWorkBook();
        //设置 标准作业书信息
        makeWorkBookI.setWorkName(workBookDto.getWorkName())
                .setUserProtectionTools(workBookDto.getUserProtectionTools())
                .setUserTools(workBookDto.getUserTools())
                .setBan(workBookDto.getBan())
                .setOther(workBookDto.getOther());
        //判断零件是否存在 并 获取其基本信息
        String uncheckName = workBookDto.getPartName();
        WarehousePart byName = warehousePartService.getByName(uncheckName);
        if (byName == null) {
            throw new PmsServiceException("当前零件名称所指零件种类不存在");
        }
        String checkName = byName.getName();
        makeWorkBookI.setWorkNo(WorkInitUtils.initWorkBookNo(checkName));
        makeWorkBookI.setPartName(checkName);
        makeWorkBookI.setPartId("UN-USE");

        //获取工序
        MakeWorkBookSeq seq = seqService.getByName(workBookDto.getSequence(), workBookDto
                .getPartName());
        if (seq == null) {
            throw new PmsServiceException("当前工序不存在，无法创建标准作业书");
        }
        makeWorkBookI.setWorkSequenceName(seq.getName());
        makeWorkBookI.setWorkSequenceId(seq.getId());

        boolean save = workBookService.save(makeWorkBookI);

        Long workBookId = makeWorkBookI.getId();
        //process list
        List<MakeWorkBookProcess> process = workBookDto.getProcess();
        if (CollectionUtils.isEmpty(process)) {
            throw new PmsServiceException("创建标准作业书失败，标准作业书详细步骤不能为空");
        }
        process.forEach(p -> {
            p.setWorkBookId(workBookId);
            //前端传入时 把 id传来了 但是在后端视角中 这个id 就是 processNo,id是数据库生成的
            p.setProcessNo(Math.toIntExact(p.getId()));
            p.setId(null);
        });
        workBookProcessService.saveBatch(process);
        return new BaseResponse<>(save);
    }

    @Transactional(rollbackFor = Exception.class)
    public MakePartProcess endStep(String code, Long userId, String userName) {

        MakePartProcess makePartProcess = new MakePartProcess();
        makePartProcess = processService.findByCode(code);
        if (makePartProcess == null) {
            throw new PmsServiceException("当前编号零件未完成加工初始化!");
        }
        String flowCode = makePartProcess.getFlowCode();
        Integer seqValue = WorkInitUtils.getSeqValue(flowCode);
seqValue++;
        WarehousePart outPartByCode = warehousePartService.getOutPartByCode(code);
        List<MakeWorkBookSeq> list = seqService.getStepsByName(outPartByCode.getName());
        int allSize = list.size();

        log.debug("flowCode:{}\nseqValue:{}", flowCode, seqValue);
        String s = WorkInitUtils.addSeqValue(flowCode);
        MakePartProcess newProcess = new MakePartProcess();
        BeanUtils.copyProperties(makePartProcess, newProcess);
        newProcess.setFlowCode(s);
        if (seqValue > 0) {
            newProcess.setStatus(1);
            if (seqValue == allSize) {
                newProcess.setStatus(3);
            }else  if (seqValue > allSize){
                throw new PmsServiceException("工序流转已经结束!");
            }
        }
        processService.saveOrUpdate(newProcess);
        //记录user recode
        MakeUserRecord makeUserRecord = new MakeUserRecord();
        makeUserRecord.setUserId(userId);
        makeUserRecord.setPartCode(code);
        makeUserRecord.setUserName(userName);
        makeUserRecord.setTime(LocalDateTime.now());
        return newProcess;
    }
}
