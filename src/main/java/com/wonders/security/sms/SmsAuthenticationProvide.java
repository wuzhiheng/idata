package com.wonders.security.sms;

import com.wonders.entity.user.User;
import com.wonders.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        String phone = (String) authenticationToken.getPrincipal();
        String smsCode = (String) authenticationToken.getCredentials();

        //验证验证码
        if(!"1234".equals(smsCode)){
            throw new BadCredentialsException("验证码不正确");
        }

        //验证用户
        User user = userService.loadUserByPhone(phone);

        if (user == null) {
            throw new UsernameNotFoundException("can't obtain user info ");
        }
        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
