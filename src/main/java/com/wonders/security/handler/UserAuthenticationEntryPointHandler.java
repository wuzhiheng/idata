package com.wonders.security.handler;

import com.wonders.util.CommonUtil;
import com.wonders.util.ResultUtil;
import com.wonders.vo.ReturnMsg;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户未登录处理类
 * @Author Sans
 * @CreateTime 2019/10/3 8:55
 */
@Component
public class UserAuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    /**
     * 用户未登录返回结果
     * @Author Sans
     * @CreateTime 2019/10/3 9:01
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        if(!CommonUtil.isAjax(request)){
            request.getSession().setAttribute("forwardUrl",request.getRequestURL());
            response.sendRedirect(request.getContextPath() + "/login");
        }else {
            ResultUtil.responseJson(response,new ReturnMsg("401","未登录",request.getRequestURL()));
        }
    }
}
