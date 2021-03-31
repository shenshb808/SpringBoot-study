package com.shb.springbootstudy.controller;


import com.shb.springbootstudy.constants.InsertValidate;
import com.shb.springbootstudy.domain.entity.SbTest;
import com.shb.springbootstudy.domain.vo.Result;
import com.shb.springbootstudy.service.ISbTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 测试表 前端控制器
 * </p>
 *
 * @author 沈涵博
 * @since 2021-03-30
 */
@RestController
@RequestMapping("/sbTest")
public class SbTestController {

    @Autowired
    private ISbTestService iSbTestService;

    @PostMapping("/insert")
    public Result insert(@Validated(value = {InsertValidate.class}) @RequestBody SbTest sbTest){
        iSbTestService.save(sbTest);
        return Result.ok();
    }


}

