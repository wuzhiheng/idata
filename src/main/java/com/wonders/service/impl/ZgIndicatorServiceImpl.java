package com.wonders.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wonders.dao.CommonDao;
import com.wonders.dao.ZgIndicatorDao;
import com.wonders.entity.ZgIndicatorEntity;
import com.wonders.service.ZgIndicatorService;
import com.wonders.vo.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 综管指标维护 服务实现类
 * </p>
 *
 * @author wuzhiheng
 * @since 2020-02-26
 */
@Service
public class ZgIndicatorServiceImpl extends ServiceImpl<ZgIndicatorDao, ZgIndicatorEntity> implements ZgIndicatorService {

    @Autowired
    private CommonDao commonDao;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Override
    @Async("myTaskExecutor")
    @Transactional
    public void execute(String taskId,String id,String sessionId) {

        String sendTo = "/session/"+sessionId;

        Map<String,Object> map = new HashMap<>();
        map.put("taskId",taskId);

        try {
            long start = System.currentTimeMillis();
            int count = 0;

            ZgIndicatorEntity zg = getById(id);

            String dataSql = zg.getDataSql();
            String deleteSql = zg.getDeleteSql();

            if (StringUtils.hasLength(deleteSql)) {
                commonDao.executeUpdate(deleteSql);
            }

            if (StringUtils.hasLength(dataSql)) {
                count  = commonDao.executeInsert(dataSql);
            }


            map.put("duration",System.currentTimeMillis() - start);
            map.put("endTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            map.put("count",count);

            simpMessagingTemplate.convertAndSend(sendTo, ReturnMsg.successTip(map));
        } catch (Exception e) {
            e.printStackTrace();
            simpMessagingTemplate.convertAndSend(sendTo, new ReturnMsg("500",e.getMessage(),map));
        }

    }
}
