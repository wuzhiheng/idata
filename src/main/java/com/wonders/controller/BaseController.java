package com.wonders.controller;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

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

    private final String OFFSET = "offset";

    private final String LIMIT = "limit";

    //统一的分页
    public void startPage(){
        int offset = Integer.parseInt(request.getParameter(OFFSET));
        int limit = Integer.parseInt(request.getParameter(LIMIT));
        PageHelper.offsetPage(offset, limit);
    }


}
