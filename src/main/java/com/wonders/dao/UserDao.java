package com.wonders.dao;

import com.wonders.entity.RoleEntity;
import com.wonders.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author wuzhiheng
 * @since 2020-03-25
 */
public interface UserDao extends BaseMapper<UserEntity> {

    @Select("select t.* from tb_user r,tb_role t,tb_user_role k " +
            "where r.id=k.user_id and t.id=k.role_id " +
            "and r.id=#{userId}")
    List<RoleEntity> getAllRole(String userId);

}
