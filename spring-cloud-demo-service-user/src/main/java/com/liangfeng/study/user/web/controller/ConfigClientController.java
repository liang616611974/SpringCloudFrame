package com.liangfeng.study.user.web.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: ConfigClientController
 * @Description:
 * @date  2018/9/14 13:57
 */
@RefreshScope
@RestController
public class ConfigClientController {

    @Value("${profile}")
    private String profile;

    //@Value("${commonP1}")
    private String commonP1;

    //@Value("${common-a-p1}")
    private String commonAP1;

    @Value("${active-file}")
    private String activeFile;

    @GetMapping("/profile")
    public String hello() {
        return this.profile;
    }

    @GetMapping("/commonAP1")
    public String commonAP1() {
        return this.commonAP1;
    }

    @GetMapping("/commonP1")
    public String commonP1() {
        return this.commonP1;
    }

    @GetMapping("/active-file")
    public String activeFile() {
        return this.activeFile;
    }



}
