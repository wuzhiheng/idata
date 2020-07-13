package com.wonders.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wonders.dao.PackageDao;
import com.wonders.dao.PackagePriceDao;
import com.wonders.entity.Package;
import com.wonders.entity.PackagePrice;
import com.wonders.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 套餐信息 服务实现类
 * </p>
 *
 * @author wuzhiheng
 * @since 2020-04-06
 */
@Service
@CacheConfig(cacheNames = "default",keyGenerator = "myKeyGenerator")
public class PackageServiceImpl extends ServiceImpl<PackageDao, Package> implements PackageService {

    @Autowired
    private PackagePriceDao packagePriceDao;

    @Override
    @Cacheable
    public List<Package> allPackages() {
        QueryWrapper<Package> query = new QueryWrapper<>();
        query.lambda()
                .eq(Package::getRemoved,"0")
                .orderByDesc(Package::getStatus)
                .orderByAsc(Package::getId);
        List<Package> packages = list(query);
        // 套餐价格设值
        for (Package p : packages) {
            QueryWrapper<PackagePrice> priceQuery = new QueryWrapper<>();
            priceQuery.lambda()
                    .eq(PackagePrice::getPackageId,p.getId())
                    .eq(PackagePrice::getRemoved,"0")
                    .orderByAsc(PackagePrice::getSeq);
            p.setPrices(packagePriceDao.selectList(priceQuery));
        }
        return packages;
    }
}
