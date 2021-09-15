package com.binarywang.demo.wx.mp.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.service.WxOAuth2Service;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class PageAuthController {


    @Autowired
    private WxMpService mpService;

    @RequestMapping("userInfo")
    public void getUserInfo(HttpServletResponse response) throws IOException {

        WxOAuth2Service wxOAuth2Service = mpService.getOAuth2Service();
        String url = wxOAuth2Service.buildAuthorizationUrl("http://invoice.nat123.net/callback","snsapi_userinfo","hello");
        response.sendRedirect(url);
    }

    @ResponseBody
    @RequestMapping("callback")
    public WxOAuth2AccessToken callback(String code,String state){
        WxOAuth2Service wxOAuth2Service = mpService.getOAuth2Service();
        try {
            WxOAuth2AccessToken wxOAuth2AccessToken = wxOAuth2Service.getAccessToken(code);
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setPrettyPrinting();
            Gson gson = gsonBuilder.create();
            System.out.println(gson.toJson(wxOAuth2AccessToken));
            WxOAuth2UserInfo wxOAuth2UserInfo = wxOAuth2Service.getUserInfo(wxOAuth2AccessToken,"zh_CN");
            System.out.println(gson.toJson(wxOAuth2UserInfo));
            return wxOAuth2AccessToken;
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("query")
    @ResponseBody
    public String query(){
            return "query";
    }

}
