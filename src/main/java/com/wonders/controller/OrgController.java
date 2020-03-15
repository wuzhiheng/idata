package com.wonders.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.wonders.entity.OrgEntity;
import com.wonders.service.OrgService;
import com.wonders.util.TreeUtil;
import com.wonders.vo.ResultList;
import com.wonders.vo.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 09:41 2020-03-08
 */
@RestController
@RequestMapping("orgTree")
public class OrgController extends BaseController{

    @Autowired
    private OrgService orgService;

    private TreeUtil<OrgEntity> treeUtil;

    public OrgController() {
        this.treeUtil = new TreeUtil<>(new TreeUtil.IRootCondition() {});
    }

    @RequestMapping("/list")
    public Object list(OrgEntity orgEntity){
        QueryWrapper<OrgEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
            .isNotNull(OrgEntity::getOrgCode)
            .like(StringUtils.hasLength(orgEntity.getOrgName()),OrgEntity::getOrgName,orgEntity.getOrgName())
        .orderByAsc(OrgEntity::getPid)
        .orderByAsc(OrgEntity::getId);

        startPage();
        List<OrgEntity> list = orgService.list(queryWrapper);
        PageInfo<OrgEntity> pageInfo = new PageInfo<>(list);

        return new ResultList(pageInfo.getTotal(),list);
    }

    @RequestMapping("/tree")
    public Object tree(){
        List<OrgEntity> list = orgService.list(null);
        return ReturnMsg.successTip(treeUtil.bulidTree(list));

    }


}
