package com.wonders.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wonders.dao.PackageDao;
import com.wonders.dao.PackagePriceDao;
import com.wonders.entity.Package;
import com.wonders.entity.PackagePrice;
import com.wonders.service.PackagePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 套餐定价信息 服务实现类
 * </p>
 *
 * @author wuzhiheng
 * @since 2020-04-06
 */
@Service
@CacheConfig(cacheNames = "default",keyGenerator = "myKeyGenerator")
public class PackagePriceServiceImpl extends ServiceImpl<PackagePriceDao, PackagePrice> implements PackagePriceService {

    @Autowired
    private PackageDao packageDao;

    @Override
    @Cacheable
    public PackagePrice getById(Integer id) {

        PackagePrice price = this.baseMapper.selectById(id);
        if(price != null){
            Package packageInfo = packageDao.selectById(price.getPackageId());
            price.setPackageInfo(packageInfo);
        }
        return price;
    }
}
