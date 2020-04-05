package com.wonders.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wonders.dao.UserDao;
import com.wonders.entity.UserEntity;
import com.wonders.properties.IDataProperties;
import com.wonders.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wuzhiheng
 * @since 2020-03-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService, UserDetailsService {

    @Autowired
    private IDataProperties iDataProperties;

    @Override
    public UserEntity loadUserByPhone(String phone) {

        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(UserEntity::getPhone,phone);

        UserEntity user = getOne(queryWrapper);

        if(user == null){
            user = new UserEntity()
                    .setPhone(phone)
                    .setNick(phone.substring(0,3)+"****"+phone.substring(7))
                    .setAvatar(iDataProperties.getFile().getDefaultAvatar());
            saveUser(user);
        }
        user.setAuthorities(Arrays.asList(new SimpleGrantedAuthority("ROLE_baiyin")));
        return user;
    }

    private void saveUser(UserEntity user){
        if(!user.getPhone().matches("^\\d{11}$")){
            throw new AuthenticationServiceException("手机号码不正确");
        }
        boolean save = save(user);
        if(!save)
            throw new AuthenticationServiceException("插入用户失败");
    }

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(UserEntity::getPhone,phone);

        UserEntity user = getOne(queryWrapper);
        if(user == null)
            throw new UsernameNotFoundException("用户不存在");
        user.setAuthorities(Arrays.asList(new SimpleGrantedAuthority("ROLE_baiyin")));
        return user;
    }
}
