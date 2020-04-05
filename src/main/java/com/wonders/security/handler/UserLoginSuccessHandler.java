package com.wonders.security.handler;

import com.wonders.dao.OperationLogDao;
import com.wonders.entity.OperationLogEntity;
import com.wonders.entity.UserEntity;
import com.wonders.global.LogManager;
import com.wonders.util.CommonUtil;
import com.wonders.util.IPUtils;
import com.wonders.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 登录成功处理类
 * @Author Sans
 * @CreateTime 2019/10/3 9:13
 */
@Slf4j
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private OperationLogDao operationLogDao;

    /**
     * 登录成功返回结果
     *
     * @Author Sans
     * @CreateTime 2019/10/3 9:27
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // 用户登录日志
        saveLog(request, authentication);

        CommonUtil.refreshSessionUser((UserEntity) authentication.getPrincipal());

        if (CommonUtil.isAjax(request)) {
            // 组装JWT
            Map<String, Object> resultData = new HashMap<>();
            resultData.put("code", "200");
            resultData.put("msg", "登录成功");
            resultData.put("token", null);

            ResultUtil.responseJson(response, resultData);
        } else {
            response.sendRedirect(request.getRequestURL().toString());
        }

    }

    // 用户登录日志
    private void saveLog(HttpServletRequest request, Authentication authentication) {
        OperationLogEntity log = new OperationLogEntity()
                .setIp(IPUtils.getIpAddr(request))
                .setBrowser(CommonUtil.browserInfo(request))
                .setUsername(((UserDetails) authentication.getPrincipal()).getUsername())
                .setBussinessType("用户操作")
                .setOperation(CommonUtil.isAjax(request) ? "用户登录" : "自动登录")
                .setRequestParam(CommonUtil.getParamDesc(request));

        LogManager.me().executeLog(() -> operationLogDao.insert(log));

    }
}
