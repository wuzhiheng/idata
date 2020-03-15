package com.wonders.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.wonders.entity.ZgIndicatorDescEntity;
import com.wonders.service.ZgIndicatorDescService;
import com.wonders.vo.ResultList;
import com.wonders.vo.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 综观指标说明 前端控制器
 * </p>
 *
 * @author wuzhiheng
 * @since 2020-03-06
 */
@RestController
@RequestMapping("/zgDesc")
@CacheConfig(cacheNames = "default")
public class ZgIndicatorDescController extends BaseController{

    @Autowired
    private ZgIndicatorDescService zgIndicatorDescService;

    @RequestMapping("list")
    public Object list(ZgIndicatorDescEntity zg) {

        QueryWrapper<ZgIndicatorDescEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .like(StringUtils.hasLength(zg.getTitle1()),
                        ZgIndicatorDescEntity::getTitle1, zg.getTitle1())
                .like(StringUtils.hasLength(zg.getTitle2()),
                        ZgIndicatorDescEntity::getTitle2, zg.getTitle2())
        ;

        startPage();

        List<ZgIndicatorDescEntity> list = zgIndicatorDescService.list(queryWrapper);

        PageInfo<ZgIndicatorDescEntity> pageInfo = new PageInfo<>(list);

        return new ResultList(pageInfo.getTotal(), list);
    }

    @RequestMapping("getById")
    @Cacheable(key = "'zg_indicator_desc_'+#id")
    public Object getById(String id) {
        ZgIndicatorDescEntity zg = zgIndicatorDescService.getById(id);
        return ReturnMsg.successTip(zg);
    }

    @RequestMapping("update")
    @CachePut(key = "'zg_indicator_desc_'+#p0.id")
    public Object update(ZgIndicatorDescEntity zg) {
        zg.setUpdateTime(new Date());
        zgIndicatorDescService.updateById(zg);
        return ReturnMsg.successTip(zg);
    }

    @RequestMapping("save")
    public Object save(ZgIndicatorDescEntity zg) {
        zgIndicatorDescService.save(zg);
        return ReturnMsg.successTip();
    }


    @RequestMapping("delete")
    public Object delete(String id) {
        zgIndicatorDescService.removeById(id);
        return ReturnMsg.successTip();
    }


}
