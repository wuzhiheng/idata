package com.wonders.security.sms;

import com.wonders.security.handler.UserLoginFailureHandler;
import com.wonders.security.handler.UserLoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 3:19 下午 2020/3/25
 */
@Component
public class SmsAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private SmsAuthenticationProvide smsAuthenticationProvide;

    /**
     * 自定义登录成功处理器
     */
    @Autowired
    private UserLoginSuccessHandler userLoginSuccessHandler;
    /**
     * 自定义登录失败处理器
     */
    @Autowired
    private UserLoginFailureHandler userLoginFailureHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //创建并配置好自定义SmsAuthenticationfilter，
        SmsAuthenticationfilter smsAuthenticationfilter = new SmsAuthenticationfilter();
        smsAuthenticationfilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsAuthenticationfilter.setAuthenticationSuccessHandler(userLoginSuccessHandler);
        smsAuthenticationfilter.setAuthenticationFailureHandler(userLoginFailureHandler);
        //创建并配置好自定义SmsAuthenticationProvide
        http.authenticationProvider(smsAuthenticationProvide);
        //将过滤器添加到过滤链路中
        http.addFilterBefore(smsAuthenticationfilter, UsernamePasswordAuthenticationFilter.class);
    }

}
