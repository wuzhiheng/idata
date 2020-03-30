package com.wonders.config;

import com.wonders.security.handler.*;
import com.wonders.security.properties.SecurityProperties;
import com.wonders.security.sms.SmsAuthenticationSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * SpringSecurity配置类
 *
 * @Author Sans
 * @CreateTime 2019/10/1 9:40
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启权限注解,默认是关闭的
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    // sms登录配置
    @Autowired
    private SmsAuthenticationSecurityConfig smsAuthenticationSecurityConfig;

    // 自定义登录成功处理器
    @Autowired
    private UserLoginSuccessHandler loginSuccessHandler;
    // 自定义登录失败处理器
    @Autowired
    private UserLoginFailureHandler loginFailureHandler;
    // 自定义注销成功处理器
    @Autowired
    private UserLogoutSuccessHandler logoutSuccessHandler;
    // 自定义暂无权限处理器
    @Autowired
    private UserAuthAccessDeniedHandler authAccessDeniedHandler;
    // 自定义未登录的处理器
    @Autowired
    private UserAuthenticationEntryPointHandler authenticationEntryPointHandler;

    // 加密方式
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PersistentTokenRepository tokenRepository;

    //remember-me持久化
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    // 配置security的控制逻辑
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //组装antMatchers
        String[] antMatchers = securityProperties.getAntMatchers().stream()
                .map(s -> s.split(","))
                .flatMap(Arrays::stream)
                .distinct()
                .toArray(String[]::new);

        http.authorizeRequests()
                // 不进行权限验证的请求或资源(从配置文件中读取)
                .antMatchers(antMatchers).permitAll()
                // 其他的需要登陆后才能访问
                .anyRequest().authenticated()
                .and()
                // 配置登录地址
                .formLogin()
                    // 配置登录成功自定义处理类
                    .successHandler(loginSuccessHandler)
                    // 配置登录失败自定义处理类
                    .failureHandler(loginFailureHandler)
                .and()
                // 配置登出地址
                .logout()
                    .logoutUrl("/logout")
                    // 配置用户登出自定义处理类
                    .logoutSuccessHandler(logoutSuccessHandler)
                .and()
                // 配置没有权限自定义处理类
                .exceptionHandling()
                    .accessDeniedHandler(authAccessDeniedHandler)// 403
                    .authenticationEntryPoint(authenticationEntryPointHandler)// 401
                .and()
                // 开启跨域
                .cors()
                .and()
                // 取消跨站请求伪造防护
                .csrf().disable()
                .rememberMe()
                    // cookie名称
                    .rememberMeCookieName(securityProperties.getRememberMeCookieName())
                    .userDetailsService(userDetailsService)
                    .tokenRepository(tokenRepository)
                    // cookie有效时间，单位s
                    .tokenValiditySeconds(securityProperties.getRememberMeSeconds())
        .and()
        .sessionManagement()
        .maximumSessions(1)
        .maxSessionsPreventsLogin(true)//false表示会踢掉前面的登录，true会禁止当前的登录
        .expiredSessionStrategy(event ->{
            event.getResponse().setContentType("application/json;charset=UTF-8");
            event.getResponse().getWriter().write("并发登陆！！！");
        })
        ;

        // 配置smsAuthenticationSecurityConfig
        http.apply(smsAuthenticationSecurityConfig);

        //默认是所有请求都加上cache-control:no-cache，需要禁掉
        http.headers().cacheControl().disable();


    }
}
