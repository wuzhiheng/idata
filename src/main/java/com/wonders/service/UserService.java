package com.wonders.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wonders.entity.user.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author wuzhiheng
 * @since 2020-03-25
 */
public interface UserService extends IService<User> {

    User loadUserByPhone(String phone);

    void updateUser(User newUser);

}
