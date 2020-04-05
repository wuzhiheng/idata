package com.wonders.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.wonders.entity.OrderEntity;
import com.wonders.service.OrderService;
import com.wonders.vo.ResultList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 订单信息 前端控制器
 * </p>
 *
 * @author wuzhiheng
 * @since 2020-04-06
 */
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @PostMapping("list")
    public ResultList list() {
        QueryWrapper<OrderEntity> query = new QueryWrapper<>();
        query.lambda()
                .eq(OrderEntity::getUserId, getUser().getId())
                .orderByDesc(OrderEntity::getOrderTime);
        startPage();
        List<OrderEntity> list = orderService.list(query);
        PageInfo<OrderEntity> pageInfo = new PageInfo<>(list);
        return new ResultList(pageInfo.getTotal(),list);
    }

}
