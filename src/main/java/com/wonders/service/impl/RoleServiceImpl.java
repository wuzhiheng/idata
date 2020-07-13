package com.wonders.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wonders.dao.RoleDao;
import com.wonders.entity.user.Role;
import com.wonders.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author wuzhiheng
 * @since 2020-04-05
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {

}
