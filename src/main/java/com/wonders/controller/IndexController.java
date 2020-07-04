package com.wonders.controller;

import com.wonders.entity.PackageEntity;
import com.wonders.service.IndexService;
import com.wonders.service.PackageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    private IndexService indexService;

    @Autowired
    private PackageService packageService;

    @GetMapping("/login")
    @ApiOperation("登录页")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String toIndex(Model model) {
        model.addAttribute("rank",indexService.rank());
        return "index";
    }

    // 行业分析-行业大盘
    @GetMapping("/analysis/industry/market")
    public String market(){
        return "pages/analysis/industry/market";
    }
    // 行业分析-行业趋势
    @GetMapping("/analysis/industry/tendency")
    public String tendency(){
        return "pages/analysis/industry/tendency";
    }
    // 行业分析-飙升分类
    @GetMapping("/analysis/industry/category")
    public String category(){
        return "pages/analysis/industry/category";
    }

    // 竞争分析
    @GetMapping("/analysis/compete")
    public String compete(){
        return "pages/analysis/compete";
    }

    // 流派分析-流派趋势
    @GetMapping("/analysis/sect/tendency")
    public String sectTendency(){
        return "pages/analysis/sect/tendency";
    }
    // 流派分析-飙升流派
    @GetMapping("/analysis/sect/up")
    public String sectUp(){
        return "pages/analysis/sect/up";
    }

    //门户网站首页
    @RequestMapping("/introduction")
    public String portal(){
        return "pages/portal/introduction";
    }

    //门户网站套餐介绍
    @GetMapping("/package")
    public String packages(Model model){
        List<PackageEntity> packages = packageService.allPackages();
        model.addAttribute("packages",packages);
        return "pages/portal/package";
    }

    //账号设置
    @GetMapping("/profile")
    public String profile(){
        return "pages/user/profile";
    }

    //会员信息
    @GetMapping("/profile/vip")
    public String vip(){
        return "pages/user/vip";
    }

    //订购记录
    @GetMapping("/profile/order")
    public String order(){
        return "pages/user/order";
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
