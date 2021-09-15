package com.binarywang.demo.wx.mp.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMenuService;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Controller
public class JsSDKController {

    @Autowired
    private WxMpService mpService;




    @ResponseBody
    @RequestMapping("apiTicket")
    public String apiTicket(){
        String ticket = null;
        try {
            ticket = mpService.getJsapiTicket();
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return ticket;
    }
    @ResponseBody
    @RequestMapping("createMenu")
    public String addMenu(){
        WxMpMenuService mpMenuService = mpService.getMenuService();
        WxMenu wxMenu = new WxMenu();
        List<WxMenuButton> buttons = new ArrayList<>();
        WxMenuButton button = new WxMenuButton();
        button.setName("发票录入");
        button.setUrl("https://www.baidu.com");
        button.setType("view");
        buttons.add(button);

        button = new WxMenuButton();
        button.setName("实名认证");
        button.setUrl("https://163.com");
        button.setType("view");
        buttons.add(button);
        wxMenu.setButtons(buttons);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

String menujson = "{\n" +
        "    \"button\": [\n" +
        "        {\n" +
        "            \"type\": \"click\", \n" +
        "            \"name\": \"今日歌曲\", \n" +
        "            \"key\": \"V1001_TODAY_MUSIC\"\n" +
        "        }, \n" +
        "        {\n" +
        "            \"name\": \"菜单\", \n" +
        "            \"sub_button\": [\n" +
        "                {\n" +
        "                    \"type\": \"view\", \n" +
        "                    \"name\": \"搜索\", \n" +
        "                    \"url\": \"http://www.soso.com/\"\n" +
        "                }, \n" +
        "                {\n" +
        "                    \"type\": \"click\", \n" +
        "                    \"name\": \"赞一下我们\", \n" +
        "                    \"key\": \"V1001_GOOD\"\n" +
        "                }\n" +
        "            ]\n" +
        "        }\n" +
        "    ]\n" +
        "}";
        System.out.println(gson.toJson(menujson));

      try {
            mpMenuService.menuCreate(menujson);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return "sucess";
    }
}
