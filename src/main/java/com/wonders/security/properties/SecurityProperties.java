package com.wonders.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 2:10 下午 2020/3/26
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.security")
public class SecurityProperties {

    // 不拦截的路径，以,分割
    private List<String> AntMatchers;

    // 记住我的时间，单位s
    private int rememberMeSeconds;

    // 短信登录的地址
    private String smsLoginUrl;

}
