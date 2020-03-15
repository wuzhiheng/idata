package com.wonders.controller;

import com.wonders.service.ZgIndicatorDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private ZgIndicatorDescService zgIndicatorDescService;

    @RequestMapping("/")
    public String toIndex() {
        return "index";
    }

    //综管指标
    @RequestMapping("page/indicator")
    public String indicator() {
        request.getSession(true);
        return "pages/indicator";
    }

    //慢病查询
    @RequestMapping("page/ncd")
    public String ncd() {
        return "pages/ncd";
    }

    //综管指标说明
    @RequestMapping("page/indicatorDesc")
    public String indicatorDesc() {
        return "pages/indicatorDesc";
    }

    //综管指标来源说明
    @RequestMapping("page/indicatorDesc/{id}")
    public String indicatorDesc_id(@PathVariable("id")String id, Model model) {
        model.addAttribute("data",zgIndicatorDescService.getById(id));
        return "pages/indicatorDesc_id";
    }

    //医疗机构树
    @RequestMapping("page/orgTree")
    public String orgTree() {
        return "pages/orgTree";
    }

    //药品管理
    @RequestMapping("page/drug")
    public String drug() {
        return "pages/drug";
    }
}
