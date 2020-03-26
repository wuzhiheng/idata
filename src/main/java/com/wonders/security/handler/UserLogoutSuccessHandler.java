package com.wonders.security.handler;

import com.wonders.util.CommonUtil;
import com.wonders.util.ResultUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登出成功处理类
 * @Author Sans
 * @CreateTime 2019/10/3 9:42
 */
@Component
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {
    /**
     * 用户登出返回结果，现改为重定向到登录页面
     * 这里应该让前端清除掉Token
     * @Author Sans
     * @CreateTime 2019/10/3 9:50
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        SecurityContextHolder.clearContext();

        if (CommonUtil.isAjax(request)){
            Map<String,Object> resultData = new HashMap<>();
            resultData.put("code","200");
            resultData.put("msg", "登出成功");
            ResultUtil.responseJson(response,ResultUtil.resultSuccess(resultData));
        }else {
            response.sendRedirect(request.getContextPath() + "/page/user/index");
        }

    }
}
