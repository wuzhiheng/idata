package com.wonders.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 11:21 2020-02-26
 */
public interface CommonDao {

    @Update("${value}")
    void executeUpdate(String sql);

    @Insert("${value}")
    Integer executeInsert(String sql);

}
