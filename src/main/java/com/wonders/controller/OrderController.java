package com.wonders.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.wonders.entity.OrderEntity;
import com.wonders.entity.PackagePriceEntity;
import com.wonders.service.OrderService;
import com.wonders.service.PackagePriceService;
import com.wonders.vo.ResultList;
import com.wonders.vo.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
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
    @Autowired
    private PackagePriceService packagePriceService;

    @PostMapping("list")
    public ResultList list() {
        QueryWrapper<OrderEntity> query = new QueryWrapper<>();
        query.lambda()
                .eq(OrderEntity::getRemoved, "0")
                .eq(OrderEntity::getUserId, getUser().getId())
                .orderByDesc(OrderEntity::getOrderTime);
        startPage();
        List<OrderEntity> list = orderService.list(query);
        PageInfo<OrderEntity> pageInfo = new PageInfo<>(list);
        return new ResultList(pageInfo.getTotal(), list);
    }

    @PostMapping("create")
    public ReturnMsg create(Integer priceId) {
        PackagePriceEntity price = packagePriceService.getById(priceId);
        OrderEntity order = new OrderEntity();
        order.setOrderTime(new Date())
                .setPayFee(price.getPayFee())
                .setDiscount(price.getDiscount())
                .setPackageFee(price.getPackageFee())
                .setOrderType("1")
                .setStatus("2")
                .setPackageId(price.getPackageId())
                .setPackageName(price.getPackageInfo().getName())
                .setPeriod(price.getPeriod())
                .setUserId(getUser().getId());

        orderService.save(order);

        return ReturnMsg.successTip();

    }

}
