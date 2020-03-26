package com.wonders.security.sms;

import com.wonders.entity.UserEntity;
import com.wonders.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 2:02 下午 2020/3/25
 */
@Component
public class SmsAuthenticationProvide implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;
        //将验证信息保存在SecurityContext以供UserDetailsService进行验证
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authenticationToken);

        String phone = (String) authenticationToken.getPrincipal();
        String smsCode = (String) authenticationToken.getCredentials();

        //验证验证码
        if(!"1234".equals(smsCode)){
            throw new BadCredentialsException("验证码不正确");
        }

        //验证用户
        UserEntity user = userService.loadUserByPhone(phone);

        if (user == null) {
            throw new InternalAuthenticationServiceException("can't obtain user info ");
        }
        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
