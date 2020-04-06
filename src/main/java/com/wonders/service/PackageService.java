package com.wonders.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wonders.entity.PackageEntity;

import java.util.List;

/**
 * <p>
 * 套餐信息 服务类
 * </p>
 *
 * @author wuzhiheng
 * @since 2020-04-06
 */
public interface PackageService extends IService<PackageEntity> {

    List<PackageEntity> allPackages();
}
