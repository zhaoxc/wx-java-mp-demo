package com.binarywang.demo.wx.mp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: java类作用描述
 * @Author: zhaoxc
 * @CreateDate: 2021/9/17$ 19:16$
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Controller
public class AutoCompileDemoController {

    @RequestMapping("login")
    public String login(){
        return "login";
    }
}
