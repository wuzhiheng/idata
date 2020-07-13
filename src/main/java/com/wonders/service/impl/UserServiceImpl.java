package com.wonders.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wonders.dao.RoleDao;
import com.wonders.dao.UserDao;
import com.wonders.dao.UserHistoryDao;
import com.wonders.dao.UserRoleDao;
import com.wonders.entity.user.Role;
import com.wonders.entity.user.User;
import com.wonders.entity.user.UserHistory;
import com.wonders.properties.IDataProperties;
import com.wonders.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
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
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService, UserDetailsService {

    @Autowired
    private IDataProperties iDataProperties;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserHistoryDao userHistoryDao;

    @Override
    public User loadUserByPhone(String phone) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(User::getPhone, phone);

        User user = getOne(queryWrapper);

        //首次登陆，自动注册
        if (user == null) {
            user = new User()
                    .setPhone(phone)
                    .setNick(phone.substring(0, 3) + "****" + phone.substring(7))
                    .setAvatar(iDataProperties.getFile().getDefaultAvatar());
            saveUser(user);
        }

        List<Role> roles = userDao.getAllRole(user.getId());
        user.setRoles(roles);
        return user;
    }

    // 更新用户信息，保存用户历史信息
    @Override
    public void updateUser(User newUser) {
        User oldUser = getById(newUser.getId());

        UserHistory userHistory = new UserHistory()
                .setUserId(oldUser.getId())
                .setPhone(oldUser.getPhone())
                .setAvatar(oldUser.getAvatar())
                .setEmail(oldUser.getEmail())
                .setNick(oldUser.getNick())
                .setStatus(oldUser.getStatus());

        userHistoryDao.insert(userHistory);
        updateById(newUser);
    }

    // 新增用户
    public void saveUser(User user) {
        if (!user.getPhone().matches("^1[3456789]\\d{9}$")) {
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
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(User::getPhone, phone);

        User user = getOne(queryWrapper);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        List<Role> roles = userDao.getAllRole(user.getId());
        user.setRoles(roles);
        return user;
    }
}
