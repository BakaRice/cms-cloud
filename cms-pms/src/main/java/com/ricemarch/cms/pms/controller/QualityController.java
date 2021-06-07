package com.ricemarch.cms.pms.controller;


import com.ricemarch.cms.pms.common.facade.BaseResponse;
import com.ricemarch.cms.pms.service.QualityPartClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}

