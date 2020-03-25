package com.wonders.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wonders.dao.UserDao;
import com.wonders.entity.UserEntity;
import com.wonders.service.UserService;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wuzhiheng
 * @since 2020-03-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Override
    public UserEntity loadUserByPhone(String phone) {

        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(UserEntity::getPhone,phone);

        UserEntity user = getOne(queryWrapper, false);

        if(user == null){
            user = new UserEntity()
                    .setPhone(phone)
                    .setNick(phone);
            saveUser(user);
        }

        return user;
    }

    private void saveUser(UserEntity user){
        if(!user.getPhone().matches("\\d{11}")){
            throw new AuthenticationException("手机号码不正确"){};
        }
        boolean save = save(user);
        if(!save)
            throw new AuthenticationException("插入用户失败"){};
    }

}
