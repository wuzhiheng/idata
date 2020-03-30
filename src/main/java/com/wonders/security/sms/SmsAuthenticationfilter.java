package com.wonders.security.sms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 1:45 下午 2020/3/25
 */
@Slf4j
public class SmsAuthenticationfilter extends AbstractAuthenticationProcessingFilter {
    private String PARAMETER_KEY_PHONE = "phone";
    private String PARAMETER_KEY_SMSCODE = "smsCode";

    public SmsAuthenticationfilter(String url) {
        super(new AntPathRequestMatcher(url, "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String mobile = request.getParameter(PARAMETER_KEY_PHONE);
        String smsCode = request.getParameter(PARAMETER_KEY_SMSCODE);
        if (mobile == null) {
            mobile="";
        }
        if(smsCode == null){
            smsCode="";
        }
        mobile = mobile.trim();
        smsCode = smsCode.trim();
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(mobile,smsCode);
        //保存ip和sessionId
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    public void setPARAMETER_KEY_PHONE(String PARAMETER_KEY_PHONE) {
        this.PARAMETER_KEY_PHONE = PARAMETER_KEY_PHONE;
    }

    public void setPARAMETER_KEY_SMSCODE(String PARAMETER_KEY_SMSCODE) {
        this.PARAMETER_KEY_SMSCODE = PARAMETER_KEY_SMSCODE;
    }
}
