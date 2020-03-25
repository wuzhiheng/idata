package com.wonders.security.sms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 1:45 下午 2020/3/25
 */
@Slf4j
public class SmsAuthenticationfilter extends AbstractAuthenticationProcessingFilter {
    private boolean postOnly = true;

    public SmsAuthenticationfilter() {
        super(new AntPathRequestMatcher("/login/smsLogin", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        log.info("进入了："+this.getClass());

        String mobile = request.getParameter("phone");
        String smsCode = request.getParameter("smsCode");
        if (mobile == null) {
            mobile="";
        }
        if(smsCode == null){
            smsCode="";
        }
        mobile = mobile.trim();
        smsCode = smsCode.trim();
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(mobile,smsCode);

        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

}
