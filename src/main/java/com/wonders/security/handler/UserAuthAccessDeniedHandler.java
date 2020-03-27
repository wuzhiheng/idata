package com.wonders.security.handler;

import com.wonders.util.ResultUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description 暂无权限处理类，注意不能在全局异常中处理，要想继续能在这个处理器处理，必须继续派出AccessDeniedException
 * @Author Sans
 * @CreateTime 2019/10/3 8:39
 */
@Component
public class UserAuthAccessDeniedHandler implements AccessDeniedHandler{
    /**
     * 暂无权限返回结果
     * @Author Sans
     * @CreateTime 2019/10/3 8:41
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception){
        ResultUtil.responseJson(response, ResultUtil.resultCode(403,"未授权"));
    }
}
