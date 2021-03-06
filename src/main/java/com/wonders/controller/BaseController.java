package com.wonders.controller;

import com.github.pagehelper.PageHelper;
import com.wonders.entity.user.User;
import com.wonders.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author : wuzhiheng
 * @Description : 给子类提供一些方法和参数
 * @Date Created in 09:43 2019-03-08
 */
public class BaseController {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    @Autowired
    protected HttpSession session;

    //统一的分页
    public void startPage(){
        int offset = Integer.parseInt(request.getParameter("offset"));
        int limit = Integer.parseInt(request.getParameter("limit"));
        PageHelper.offsetPage(offset, limit);
    }

    public Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    // 注意，这里获取的user其实就是authentication里面的UserDetails
    public User getUser(){
        return CommonUtil.getSessionUser();
    }

}
