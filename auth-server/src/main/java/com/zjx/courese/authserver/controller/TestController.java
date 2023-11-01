package com.zjx.courese.authserver.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test1")
    public String test() {
        return "测试";
    }


    @GetMapping("/test2")
    public String demo() {
        return "这是demo接口";
    }
}


