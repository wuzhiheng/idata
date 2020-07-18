package com.wonders.service;

import com.wonders.dao.IndexDao;
import com.wonders.entity.GlInfo;
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

    public GlInfo glInfo(){
        return indexDao.glInfo("20200619");
    }
}
