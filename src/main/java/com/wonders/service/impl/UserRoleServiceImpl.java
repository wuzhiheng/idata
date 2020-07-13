package com.wonders.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wonders.dao.UserRoleDao;
import com.wonders.entity.user.UserRole;
import com.wonders.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户-角色表 服务实现类
 * </p>
 *
 * @author wuzhiheng
 * @since 2020-04-05
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRole> implements UserRoleService {

}
