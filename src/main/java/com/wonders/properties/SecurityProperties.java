package com.wonders.properties;

import lombok.Data;

import java.util.List;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 1:31 下午 2020/4/5
 */
@Data
public class SecurityProperties {

    // 不拦截的路径，以,分割
    private List<String> antMatchers;

    // 记住我的时间，单位s
    private int rememberMeSeconds;

    // 短信登录的地址
    private String smsLoginUrl;

    // 记住我的cookie名称
    private String rememberMeCookieName;

}
