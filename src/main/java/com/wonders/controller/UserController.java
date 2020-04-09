package com.wonders.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wonders.entity.UserEntity;
import com.wonders.properties.IDataProperties;
import com.wonders.service.UserService;
import com.wonders.util.ProxyUtil;
import com.wonders.vo.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author wuzhiheng
 * @since 2020-03-25
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController extends BaseController {

    @Autowired
    private IDataProperties iDataProperties;
    @Autowired
    private UserService userService;
    @Autowired
    @Lazy
    private RememberMeServices rememberMeService;

    private volatile PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @PostMapping("/save")
    public ReturnMsg save(@NotBlank(message = "昵称不能为空") String nick,String email){
        UserEntity user = getUser();
        user.setNick(nick);
        user.setEmail(email);
        userService.updateUser(user);

        return ReturnMsg.successTip();
    }

    @PostMapping("/avatar/upload")
    public Object avatar_upload(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return ReturnMsg.errorTip("文件不能为空");
        }
        if (!file.getOriginalFilename().matches(".*(jpg|jpeg|png|gif)$")) {
            return ReturnMsg.errorTip("图片格式错误");
        }

        String avatarPath = getAvatarPath(file);
        File avatarFile = new File(iDataProperties.getFile().getAvatarLocation() + "/" + avatarPath);

        if (!avatarFile.getParentFile().exists()) {
            avatarFile.getParentFile().mkdirs();
        }

        FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(avatarFile));

        UserEntity user = getUser();
        String oldAvatar = user.getAvatar();
        user.setAvatar(iDataProperties.getFile().getAvatarPath() + "/" + avatarPath);

        // 这里try-catch是为了防止出错后authentication已经生效为新的
        try {
            userService.updateUser(user);
        } catch (Exception e) {
            getUser().setAvatar(oldAvatar);
            throw e;
        }

        return ReturnMsg.successTip(user.getAvatar());
    }

    @PostMapping("/bindPhone")
    public ReturnMsg bindPhone(@NotNull(message = "验证码不能为空") @Pattern(regexp = "^1234$", message = "验证码不正确") String code,
                               @NotNull(message = "手机号不能为空") @Pattern(regexp = "^1[3456789]\\d{9}$", message = "手机号码不正确") String phone) {
        checkPhone(phone);
        UserEntity user = getUser();
        String olePhone = user.getPhone();
        user.setPhone(phone);
        userService.updateUser(user);

        Authentication oldAuthentication = new UsernamePasswordAuthenticationToken(new UserEntity().setPhone(olePhone),null,null);
        refreshAuthentication(request, response, oldAuthentication);

        return ReturnMsg.successTip();
    }

    // 刷新authentication和cookie
    private void refreshAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication oldAuthentication) {
        if(checkCookie(request)){
            // 先删除就的token然后创建新的token
            getPersistentTokenBasedRememberMeServices().logout(request,response,oldAuthentication);
            rememberMeService.loginSuccess(request, response, getAuthentication());
        }
    }

    // 判断是否有rm的cookie，有才执行remember-me的后续
    private boolean checkCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies) {
                if(iDataProperties.getSecurity().getRememberMeCookieName().equals(cookie.getName()))
                    return true;
            }
        }
        return false;
    }

    // 一个手机只能绑定一个用户
    private void checkPhone(String phone) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(UserEntity::getPhone, phone);
        if (userService.getOne(queryWrapper) != null)
            throw new RuntimeException("该手机已绑定其他用户");
    }

    // 拼接头像存储的地址
    private String getAvatarPath(MultipartFile file) {
        String filename = file.getOriginalFilename();
        return getUser().getPhone() + "/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + filename.substring(filename.lastIndexOf("."));
    }

    // 单例-加锁
    private PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
        if(persistentTokenBasedRememberMeServices == null){
            synchronized (this){
                if(persistentTokenBasedRememberMeServices == null) {
                    try {
                        persistentTokenBasedRememberMeServices =
                                (PersistentTokenBasedRememberMeServices) ProxyUtil.getJdkDynamicProxyTargetObject(rememberMeService);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return persistentTokenBasedRememberMeServices;
    }
}
