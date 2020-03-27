package com.wonders.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * 专门处理网页的controller
 */
@Controller
@RequestMapping
@Api(tags = "专门处理网页的controller")
public class IndexController extends BaseController {

    @GetMapping("/")
    public String toIndex() {
        return "index";
    }

    @GetMapping("/login")
    @ApiOperation("登录页")
    public String login() {
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
    @GetMapping("page/**")
    public String page() {
        return "pages/" + request.getServletPath().replaceFirst("page/", "");
    }

    // 动态修改权限
    public void dynamic() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //  生成当前的所有授权
        List<GrantedAuthority> updatedAuthorities = new ArrayList<>(authentication.getAuthorities());
        // 添加 ROLE_VIP 授权
        updatedAuthorities.add(new SimpleGrantedAuthority("ROLE_VIP"));
        // 生成新的认证信息
        Authentication newAuth = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), updatedAuthorities);
        // 重置认证信息
        SecurityContextHolder.getContext().setAuthentication(newAuth);


    }


}
