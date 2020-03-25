package com.wonders.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @projectName:common-web
 * @packageName:com.wonders.commonweb.controller
 * @authorName:wangjiaming
 * @createDate:2019-08-29
 * @editor:IntelliJ IDEA
 * @other:
 **/
@Controller
@RequestMapping
public class IndexController extends BaseController{

    @RequestMapping("/")
    public String toIndex() {
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

//    //门户网站首页
//    @RequestMapping("page/portal")
//    public String portal(){
//        return "pages/portal/index";
//    }
//    //门户网站套餐介绍
//    @RequestMapping("page/package")
//    public String packages(){
//        return "pages/portal/package";
//    }
//
//    //个人中心
//    @RequestMapping("page/user")
//    public String user(){
//        return "pages/user/index";
//    }

    //网页跳转-后期细分
    @RequestMapping("page/**")
    public String page(){
        return "pages/"+request.getServletPath().replaceFirst("page/","");
    }



}
