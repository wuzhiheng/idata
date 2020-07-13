package com.wonders.service;

import com.wonders.dao.IndexDao;
import com.wonders.entity.BookRank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 10:21 下午 2020/7/4
 */
@Service
public class IndexService {

    @Autowired
    private IndexDao indexDao;

    public BookRank rank(){
        return indexDao.rank("1013926412","400732140");
    }
}
