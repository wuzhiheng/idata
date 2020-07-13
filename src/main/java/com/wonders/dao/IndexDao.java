package com.wonders.dao;

import com.wonders.entity.BookRank;
import org.apache.ibatis.annotations.Select;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 10:18 下午 2020/7/4
 */
public interface IndexDao {

    @Select("select a.rank_1,a.rank_2,a.rank_3,a.rank_4," +
            "       b.rank_1 - a.rank_1 as rank1_rate," +
            "       b.rank_2 - a.rank_2 as rank2_rate," +
            "       b.rank_3 - a.rank_3 as rank3_rate," +
            "       b.rank_4 - a.rank_4 as rank4_rate " +
            "from idata2.tb_rank_log a " +
            "left join idata2.tb_rank_log b " +
            "on (a.author_id=b.author_id and a.book_id=b.book_id and a.batch_no -1 = b.batch_no)" +
            "where a.author_id=#{authorId} and a.book_id=#{bookId} and a.batch_no='20200619'")
    BookRank rank(String bookId, String authorId);

}
