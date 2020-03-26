package com.wonders.security.sms;

import com.wonders.security.handler.UserLoginFailureHandler;
import com.wonders.security.handler.UserLoginSuccessHandler;
import com.wonders.security.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @Author : wuzhiheng
 * @Description : sms短信验证码登录配置
 * @Date Created in 3:19 下午 2020/3/25
 */
@Component
public class SmsAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private SmsAuthenticationProvide smsAuthenticationProvide;
    @Autowired
    private SecurityProperties securityProperties;
    /**
     * 自定义登录成功处理器
     */
    @Autowired
    private UserLoginSuccessHandler loginSuccessHandler;
    /**
     * 自定义登录失败处理器
     */
    @Autowired
    private UserLoginFailureHandler loginFailureHandler;

    @Override
    public void configure(HttpSecurity http){
        //创建并配置好自定义SmsAuthenticationfilter，
        SmsAuthenticationfilter smsAuthenticationfilter = new SmsAuthenticationfilter(securityProperties.getSmsLoginUrl());
        smsAuthenticationfilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsAuthenticationfilter.setRememberMeServices(http.getSharedObject(RememberMeServices.class));
        smsAuthenticationfilter.setAuthenticationSuccessHandler(loginSuccessHandler);
        smsAuthenticationfilter.setAuthenticationFailureHandler(loginFailureHandler);

        //创建并配置好自定义SmsAuthenticationProvide
        http.authenticationProvider(smsAuthenticationProvide);
        //将过滤器添加到过滤链路中
        http.addFilterBefore(smsAuthenticationfilter, UsernamePasswordAuthenticationFilter.class);
    }

}
