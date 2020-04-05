package com.wonders.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author : wuzhiheng
 * @Description : idata个性化配置
 * @Date Created in 1:19 下午 2020/4/5
 */
@Component
@Data
@ConfigurationProperties(prefix = "idata")
public class IDataProperties {

    private SecurityProperties security;

    private FileProperties file;

}

