package com.wonders.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.DigestUtils;

import java.lang.reflect.Method;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 10:30 下午 2020/4/6
 */
@Configuration
@Slf4j
public class CacheConfig {

    @Bean
    public KeyGenerator myKeyGenerator(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                //PackagePriceServiceImpl#getById&1
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getSimpleName());
                sb.append("#");
                sb.append(method.getName());
                for (Object obj : params) {
                    if (obj != null){
                        sb.append("&");
                        try {
                            sb.append(new ObjectMapper().writeValueAsString(obj));
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return DigestUtils.md5DigestAsHex(sb.toString().getBytes());
            }
        };
    }



}
