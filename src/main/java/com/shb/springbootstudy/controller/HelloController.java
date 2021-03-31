package com.shb.springbootstudy.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shb.springbootstudy.domain.entity.SbTest;
import com.shb.springbootstudy.service.ISbTestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "hello控制器")
public class HelloController {

    @Autowired
    private ISbTestService iSbTestService;

    @GetMapping("/hello")
    @ApiOperation(value = "hello方法")
    public Object hello(Page page){
        return iSbTestService.page(page);
    }

    @PostMapping("/insert")
    @ApiOperation(value = "新增")
    public void insert(SbTest sbTest){
        iSbTestService.save(sbTest);
    }

    @PutMapping("/update")
    @ApiOperation(value = "更新")
    public void update(SbTest sbTest){
        iSbTestService.updateById(sbTest);
    }
}
