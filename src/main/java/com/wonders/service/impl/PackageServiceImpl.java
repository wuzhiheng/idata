package com.wonders.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wonders.dao.PackageDao;
import com.wonders.dao.PackagePriceDao;
import com.wonders.entity.PackageEntity;
import com.wonders.entity.PackagePriceEntity;
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
public class PackageServiceImpl extends ServiceImpl<PackageDao, PackageEntity> implements PackageService {

    @Autowired
    private PackagePriceDao packagePriceDao;

    @Override
    @Cacheable
    public List<PackageEntity> allPackages() {
        QueryWrapper<PackageEntity> query = new QueryWrapper<>();
        query.lambda()
                .eq(PackageEntity::getRemoved,"0")
                .orderByDesc(PackageEntity::getStatus)
                .orderByAsc(PackageEntity::getId);
        List<PackageEntity> packages = list(query);
        // 套餐价格设值
        for (PackageEntity p : packages) {
            QueryWrapper<PackagePriceEntity> priceQuery = new QueryWrapper<>();
            priceQuery.lambda()
                    .eq(PackagePriceEntity::getPackageId,p.getId())
                    .eq(PackagePriceEntity::getRemoved,"0")
                    .orderByAsc(PackagePriceEntity::getSeq);
            p.setPrices(packagePriceDao.selectList(priceQuery));
        }
        return packages;
    }
}
