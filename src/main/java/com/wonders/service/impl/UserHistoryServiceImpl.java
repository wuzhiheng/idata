package com.wonders.service.impl;

import com.wonders.entity.UserHistoryEntity;
import com.wonders.dao.UserHistoryDao;
import com.wonders.service.UserHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表-修改历史 服务实现类
 * </p>
 *
 * @author wuzhiheng
 * @since 2020-04-08
 */
@Service
public class UserHistoryServiceImpl extends ServiceImpl<UserHistoryDao, UserHistoryEntity> implements UserHistoryService {

}
