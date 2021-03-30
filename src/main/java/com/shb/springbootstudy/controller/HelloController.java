package com.shb.springbootstudy.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "hello控制器")
public class HelloController {

    @GetMapping("/hello")
    @ApiOperation(value = "hello方法")
    public String hello(){
        return "Hello!SpringBoot!";
    }
}
