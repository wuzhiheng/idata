package com.wonders.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.wonders.entity.PmWarehouseInEntity;
import com.wonders.service.PmWarehouseInService;
import com.wonders.vo.ResultList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 药库入库监控 前端控制器
 * </p>
 *
 * @author wuzhiheng
 * @since 2020-03-08
 */
@RestController
@RequestMapping("/drug")
public class PmWarehouseInController extends BaseController{

    @Autowired
    private PmWarehouseInService pmWarehouseInService;

    @RequestMapping("list")
    public Object list(PmWarehouseInEntity entity){

        QueryWrapper<PmWarehouseInEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .like(StringUtils.hasLength(entity.getMedicalInstitutionName()),
                        PmWarehouseInEntity::getMedicalInstitutionName,entity.getMedicalInstitutionName())
                .like(StringUtils.hasLength(entity.getItemName()),PmWarehouseInEntity::getItemName,entity.getItemName())
                .eq(entity.getInStorageDate() != null,PmWarehouseInEntity::getInStorageDate,entity.getInStorageDate())
                .orderByDesc(PmWarehouseInEntity::getSuppliedDate);

        startPage();
        List<PmWarehouseInEntity> list = pmWarehouseInService.list(queryWrapper);
        PageInfo<PmWarehouseInEntity> pageInfo = new PageInfo<>(list);

        return new ResultList(pageInfo.getTotal(), list);
    }

}
