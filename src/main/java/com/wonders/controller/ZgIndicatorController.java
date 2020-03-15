package com.wonders.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.wonders.dao.CommonDao;
import com.wonders.entity.ZgIndicatorEntity;
import com.wonders.service.ZgIndicatorService;
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
 * 综管指标维护 前端控制器
 * </p>
 *
 * @author wuzhiheng
 * @since 2020-02-26
 */
@RestController
@RequestMapping("/zg")
@CacheConfig(cacheNames = "default")
public class ZgIndicatorController extends BaseController {

    @Autowired
    private ZgIndicatorService zgService;

    @Autowired
    private CommonDao commonDao;

    @RequestMapping("list")
    public Object list(ZgIndicatorEntity zg) {

        QueryWrapper<ZgIndicatorEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(StringUtils.hasLength(zg.getStatus()),
                        ZgIndicatorEntity::getStatus, zg.getStatus())
                .eq(StringUtils.hasLength(zg.getHzbm()),
                        ZgIndicatorEntity::getHzbm, zg.getHzbm())
                .like(StringUtils.hasLength(zg.getZtmc()),
                        ZgIndicatorEntity::getZtmc, zg.getZtmc())
                .like(StringUtils.hasLength(zg.getZbmc()),
                        ZgIndicatorEntity::getZbmc, zg.getZbmc())
                .like(StringUtils.hasLength(zg.getTbmc()),
                        ZgIndicatorEntity::getZbmc, zg.getTbmc())
        ;

        startPage();

        List<ZgIndicatorEntity> list = zgService.list(queryWrapper);

        PageInfo<ZgIndicatorEntity> pageInfo = new PageInfo<>(list);

        return new ResultList(pageInfo.getTotal(), list);
    }

    @RequestMapping("getById")
    @Cacheable(key = "'zg_indicator_'+#id")
    public Object getById(String id) {
        ZgIndicatorEntity zg = zgService.getById(id);
        return ReturnMsg.successTip(zg);
    }

    @RequestMapping("update")
    @CachePut(key = "'zg_indicator_'+#p0.id")
    public Object update(ZgIndicatorEntity zg) {
        zg.setUpdateTime(new Date());
        zgService.updateById(zg);
        return ReturnMsg.successTip(zg);
    }

    @RequestMapping("save")
    public Object save(ZgIndicatorEntity zg) {
        zgService.save(zg);
        return ReturnMsg.successTip();
    }

    @RequestMapping("delete")
    public Object delete(String id) {
        return updateStatus(new ZgIndicatorEntity().setId(id).setStatus("0"));
    }

    /**
     *  执行sql
     */
    @RequestMapping("execute")
    public Object execute(String taskId,String id) {
        String sessionId = session.getId();
        //异步任务
        zgService.execute(taskId,id,sessionId);

        return ReturnMsg.successTip();
    }

    @RequestMapping("updateStatus")
    public Object updateStatus(ZgIndicatorEntity zg) {
        zg.setUpdateTime(new Date());
        zgService.updateById(zg);
        return ReturnMsg.successTip();
    }

}
