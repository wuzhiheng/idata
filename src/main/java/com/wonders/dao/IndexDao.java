package com.wonders.dao;

import com.wonders.entity.BookBaseInfo;
import com.wonders.entity.BookRank;
import com.wonders.entity.GlInfo;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 10:18 下午 2020/7/4
 */
public interface IndexDao {

    BookRank rank(String bookId, String authorId,String batchNo);

    BookBaseInfo baseInfo(String bookId, String authorId,String batchNo);

    GlInfo glInfo(String batchNo);

}
