package com.wonders.security.handler;

import com.wonders.dao.OperationLogDao;
import com.wonders.entity.Author;
import com.wonders.entity.user.OperationLog;
import com.wonders.entity.user.User;
import com.wonders.global.LogManager;
import com.wonders.properties.IDataProperties;
import com.wonders.service.AuthorService;
import com.wonders.util.CommonUtil;
import com.wonders.util.IPUtils;
import com.wonders.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
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
    @Autowired
    private IDataProperties iDataProperties;
    @Autowired
    private AuthorService authorService;

    /**
     * 登录成功返回结果
     *
     * @Author Sans
     * @CreateTime 2019/10/3 9:27
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 用户登录日志
        saveLog(request, authentication);

        CommonUtil.saveSessionUser(request);

        saveAuthorInfo(request);

        // 常规的通过ajax请求登录，返回json
        if (isAjaxLogin(request)) {
                Map<String, Object> resultData = new HashMap<>();
                resultData.put("code", "200");
                resultData.put("msg", "登录成功");
                resultData.put("token", null);
                ResultUtil.responseJson(response, resultData);
        } else {
            // session过期，自动登录后要重新转发请求 request.getServletPath()
            String forwardPath = request.getRequestURI().replaceFirst(request.getContextPath(), "");
            log.info("forward : {}",forwardPath);
            request.getRequestDispatcher(forwardPath).forward(request,response);
        }

    }

    private void saveAuthorInfo(HttpServletRequest request) {
        Author author = authorService.getAuthor("3228548");
        request.getSession().setAttribute("author",author);

    }

    // 用户登录日志
    private void saveLog(HttpServletRequest request, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        OperationLog log = new OperationLog()
                .setUserId(user.getId())
                .setIp(IPUtils.getIpAddr(request))
                .setBrowser(CommonUtil.browserInfo(request))
                .setUsername(user.getPhone())
                .setBussinessType("用户操作")
                .setOperation(isAjaxLogin(request) ? "用户登录" : "自动登录")
                .setRequestParam(CommonUtil.getParamDesc(request));

        LogManager.me().executeLog(() -> operationLogDao.insert(log));
    }

    // 判断是否通过常规登录 /login/smsLogin
    private boolean isAjaxLogin(HttpServletRequest request){
        return iDataProperties.getSecurity().getSmsLoginUrl().equals(request.getServletPath());
    }

}
