package com.wonders.service.impl;

import com.wonders.entity.OrderEntity;
import com.wonders.dao.OrderDao;
import com.wonders.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单信息 服务实现类
 * </p>
 *
 * @author wuzhiheng
 * @since 2020-04-06
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {

}
