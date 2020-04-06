package com.wonders.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wonders.entity.UserEntity;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author wuzhiheng
 * @since 2020-03-25
 */
public interface UserService extends IService<UserEntity> {

    UserEntity loadUserByPhone(String phone);

    void updateUser(UserEntity newUser);

}
