package com.wonders.service;

import com.wonders.dao.IndexDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 10:21 下午 2020/7/4
 */
@Service
public class IndexService {

    @Autowired
    private IndexDao indexDao;

    public Map<String,Object> rank(){
        return indexDao.rank("1013926412","400732140");
    }
}
