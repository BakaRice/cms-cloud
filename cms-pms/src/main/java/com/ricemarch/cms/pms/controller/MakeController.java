package com.ricemarch.cms.pms.controller;

import com.ricemarch.cms.pms.common.component.WorkInitUtils;
import com.ricemarch.cms.pms.common.expection.PmsServiceException;
import com.ricemarch.cms.pms.common.facade.BaseResponse;
import com.ricemarch.cms.pms.dto.make.SeqBookDto;
import com.ricemarch.cms.pms.dto.make.WorkBookDto;
import com.ricemarch.cms.pms.dto.wms.StepsDto;
import com.ricemarch.cms.pms.entity.*;
import com.ricemarch.cms.pms.service.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author RiceMarch
 * @date 2021/5/21 10:23
 */
@Slf4j
@RestController
@RequestMapping("/api/pms/make")
public class MakeController extends BaseController {


    /***
     * 加工流程说明
     * 1. 零件从供应商手中进行入库，（由零件名称直接确定零件种类的唯一性，由零件编码code确定零件的唯一性）
     * 2. 对零件的加工流程（工序）进行初始化，规定各个加工流程，确认加工的检查等级 ，存入表
     * 3. 检查人员对零件种类进行3D模型的上传，选中指定的工序 对应的区域进行检查要求的规定。
     * 4. 生产人员对零件从仓储中进行出库，出库后进行生产初始化，初始化这个东西（1111 0000 0000 .... 0000 1111) (根据上文的要求进行初始化）
     */

    @Autowired
    MakeTxService makeTxService;

    @Autowired
    MakeWorkBookSeqService seqService;

    @Autowired
    MakeWorkBookService workBookService;

    @Autowired
    WarehousePartService warehousePartService;

    @Autowired
    MakeWorkBookProcessService workBookProcessService;

    @Autowired
    MakePartProcessService makePartProcessService;

    @ApiOperation("[PUSH]读入标准作业书,标准作业数是对工序内的执行细节进行约束的,")
    @PostMapping("/work-book")
    public BaseResponse<Boolean> initWrokBook(@RequestBody WorkBookDto workBookDto) {
        return makeTxService.initWorkBook(workBookDto);
    }

    @ApiOperation("根据workno获取标准作业书")
    @GetMapping("/work-book")
    public BaseResponse<WorkBookDto> getWorkBookByWorkNo(@RequestParam String workNo) {
        WorkBookDto workBookDto = workBookService.getWorkBookByWorkNo(workNo);
        return new BaseResponse<>(workBookDto);
    }

    @ApiOperation("[PUSH]初始化工序")
    @PostMapping("/steps")
    public BaseResponse<Boolean> initSteps(@RequestBody StepsDto stepsDto) {

        String partName = stepsDto.getPartName();
        if (StringUtils.isBlank(partName)) {
            throw new PmsServiceException("零件种类编码不能为空");
        }
        WarehousePart byName = warehousePartService.getByName(partName);
        if (byName == null) {
            throw new PmsServiceException("当前零件名称所指零件种类不存在，请检查零件名称");
        }
        stepsDto.getSeqList().forEach(u -> {
            u.setCode(stepsDto.getPartName());
        });
        try {
            seqService.saveBatch(stepsDto.getSeqList());
        } catch (Exception e) {
            throw new PmsServiceException("当前零件初始化工序失败，检查工序设置");
        }
        return new BaseResponse<>();
    }

    @ApiOperation("初始化加工")
    @GetMapping("/init")
    public BaseResponse<Boolean> onProcess(@RequestParam String code) {
        Long userId = getUserId();
        String userName = getUserName();
        //code 是初始化的零件编号
        // FOR TX
        Boolean is_init = makeTxService.initProcess(code, userId, userName);
        if (!is_init) {
            throw new PmsServiceException("加工初始化失败！");
        }
        return new BaseResponse<>(is_init);
    }


    @ApiOperation("根据零件名查找工序")
    @GetMapping("/steps")
    public BaseResponse<List<MakeWorkBookSeq>> getSteps(@RequestParam String name) {
        List<MakeWorkBookSeq> list = seqService.getStepsByName(name);
        return new BaseResponse<>(list);
    }

    @ApiOperation("根据零件名查找工序和作业名")
    @GetMapping("/stepDto")
    public BaseResponse<List<SeqBookDto>> getStepDto(@RequestParam String name) {
        List<SeqBookDto> list = seqService.getSeqBookDtos(name);
        return new BaseResponse<>(list);
    }


    @ApiOperation("根据零件code获取零件个体信息")
    @GetMapping("/part")
    public BaseResponse<WarehousePart> getPartByCode(@RequestParam String code){
        WarehousePart warehousePart = warehousePartService.getOutPartByCode(code);
        return new BaseResponse<>(warehousePart);
    }

    @ApiOperation("根据零件code获取零件加工信息")
    @GetMapping("/workInfo")
    public BaseResponse<MakePartProcess> getWorkInfoByCode(@RequestParam String code){
        MakePartProcess makePartProcess = makePartProcessService.findByCode(code);
        return new BaseResponse<>(makePartProcess);
    }

}
