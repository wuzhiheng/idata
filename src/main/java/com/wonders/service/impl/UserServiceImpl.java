package com.wonders.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wonders.dao.RoleDao;
import com.wonders.dao.UserDao;
import com.wonders.dao.UserRoleDao;
import com.wonders.entity.RoleEntity;
import com.wonders.entity.UserEntity;
import com.wonders.properties.IDataProperties;
import com.wonders.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wuzhiheng
 * @since 2020-03-25
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService, UserDetailsService {

    @Autowired
    private IDataProperties iDataProperties;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private UserDao userDao;

    @Override
    public UserEntity loadUserByPhone(String phone) {

        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(UserEntity::getPhone,phone);

        UserEntity user = getOne(queryWrapper);

        //首次登陆，自动注册
        if(user == null){
            user = new UserEntity()
                    .setPhone(phone)
                    .setNick(phone.substring(0,3)+"****"+phone.substring(7))
                    .setAvatar(iDataProperties.getFile().getDefaultAvatar());
            saveUser(user);
        }

        List<RoleEntity> roles = userDao.getAllRole(user.getId());
        List<GrantedAuthority> authorityList =
                AuthorityUtils.createAuthorityList(roles.stream().map(RoleEntity::getName).toArray(String[]::new));
        user.setAuthorities(authorityList);

        return user;
    }

    // 新增用户
    public void saveUser(UserEntity user){
        if(!user.getPhone().matches("^\\d{11}$")){
            throw new AuthenticationServiceException("手机号码不正确");
        }
        save(user);
//        // 赋予默认角色-baiyin
//        Wrapper<RoleEntity> query = new QueryWrapper<RoleEntity>()
//                .lambda()
//                .eq(RoleEntity::getName, "baiyin");
//        RoleEntity role = roleDao.selectOne(query);
//
//        // 保存用户-角色关系
//        UserRoleEntity userRole = new UserRoleEntity()
//                .setRoleId(role.getId())
//                .setUserId(user.getId());
//        userRoleDao.insert(userRole);

    }

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(UserEntity::getPhone,phone);

        UserEntity user = getOne(queryWrapper);
        if(user == null)
            throw new UsernameNotFoundException("用户不存在");
        List<RoleEntity> roles = userDao.getAllRole(user.getId());
        List<GrantedAuthority> authorityList =
                AuthorityUtils.createAuthorityList(roles.stream().map(RoleEntity::getName).toArray(String[]::new));
        user.setAuthorities(authorityList);
        return user;
    }
}
