package com.liangfeng.study.zuul.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: IndexController
 * @Description:
 * @date  2018/10/3 20:24
 */
@RestController
public class IndexController {

    @RequestMapping(value = "/index")
    public String index(ModelMap map, HttpSession httpSession) {
        map.put("title", "第一个应用：sessionID=" + httpSession.getId());
        System.out.println("sessionID=" + httpSession.getId());
        return  "第一个应用：sessionID=" + httpSession.getId();
    }
}
