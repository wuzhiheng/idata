package com.wonders.dao;

import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 10:18 下午 2020/7/4
 */
public interface IndexDao {

    @Select("select rank_1,rank_2,rank_3,rank_4 from idata2.tb_qidian_book " +
            "where authorid = #{authorId}" +
            "  and bookid = #{bookId}")
    Map<String,Object> rank(String bookId,String authorId);

}
