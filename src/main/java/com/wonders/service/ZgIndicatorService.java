package com.wonders.service;

import com.wonders.entity.ZgIndicatorEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 综管指标维护 服务类
 * </p>
 *
 * @author wuzhiheng
 * @since 2020-02-26
 */
public interface ZgIndicatorService extends IService<ZgIndicatorEntity> {

    void execute(String taskId,String id,String sessionId);
}
