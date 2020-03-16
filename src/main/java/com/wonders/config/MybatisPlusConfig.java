package com.wonders.config;

import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author : wuzhiheng
 * @Description : 配置打印mybatis执行的sql和执行时间
 * @Date Created in 11:24 上午 2020/3/16
 */
@Configuration
public class MybatisPlusConfig {

    @Bean
    public PerformanceInterceptor performanceInterceptor(){
        return new PerformanceInterceptor();
    }

}
