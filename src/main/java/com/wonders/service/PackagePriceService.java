package com.wonders.service;

import com.wonders.entity.PackagePrice;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 套餐定价信息 服务类
 * </p>
 *
 * @author wuzhiheng
 * @since 2020-04-06
 */
public interface PackagePriceService extends IService<PackagePrice> {

    PackagePrice getById(Integer id);

}
