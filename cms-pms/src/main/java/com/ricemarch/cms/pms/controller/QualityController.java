package com.ricemarch.cms.pms.controller;


import com.ricemarch.cms.pms.common.facade.BaseResponse;
import com.ricemarch.cms.pms.entity.QualityPartClaim;
import com.ricemarch.cms.pms.service.QualityPartClaimService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ricemarch
 * @since 2021-05-21
 */
@RestController
@RequestMapping("/api/pms/quality")
public class QualityController {

    @Autowired
    QualityPartClaimService qualityPartClaimService;

    @GetMapping
    public BaseResponse<String> getLevelCode(@RequestParam String name) {
        return new BaseResponse<>(qualityPartClaimService.getByPartName(name));
    }

    @PostMapping
    public BaseResponse<Boolean> postLevelCide(@RequestBody QualityPartClaim qualityPartClaim) {
        QualityPartClaim qualityPartClaim1 = new QualityPartClaim();
        BeanUtils.copyProperties(qualityPartClaim, qualityPartClaim1);
        boolean save = qualityPartClaimService.save(qualityPartClaim1);
        return new BaseResponse<>(save);
    }

}

