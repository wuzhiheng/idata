package com.wonders.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wonders.dao.OperationLogDao;
import com.wonders.entity.user.OperationLog;
import com.wonders.service.OperationLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志 服务实现类
 * </p>
 *
 * @author wuzhiheng
 * @since 2020-04-05
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogDao, OperationLog> implements OperationLogService {

}
