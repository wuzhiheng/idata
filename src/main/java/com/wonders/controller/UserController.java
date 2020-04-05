package com.wonders.controller;


import com.wonders.entity.UserEntity;
import com.wonders.properties.IDataProperties;
import com.wonders.service.UserService;
import com.wonders.util.CommonUtil;
import com.wonders.vo.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
public class UserController extends BaseController{

    @Autowired
    private IDataProperties iDataProperties;
    @Autowired
    private UserService userService;

    @PostMapping("/avatar/upload")
    public Object avatar_upload(MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return ReturnMsg.errorTip("文件不能为空");
        }
        if(!file.getOriginalFilename().matches(".*(jpg|jpeg|png|gif)$")){
            return ReturnMsg.errorTip("图片格式错误");
        }

        String avatarPath = getAvatarPath(file);
        File avatarFile = new File(iDataProperties.getFile().getAvatarLocation()+"/"+avatarPath);

        if(!avatarFile.getParentFile().exists()){
            avatarFile.getParentFile().mkdirs();
        }

        FileCopyUtils.copy(file.getInputStream(),new FileOutputStream(avatarFile));

        UserEntity user = getUser();
        user.setAvatar(iDataProperties.getFile().getAvatarPath()+"/"+avatarPath);
        userService.updateById(user);

        CommonUtil.refreshSessionUser(user);

        return ReturnMsg.successTip(user.getAvatar());
    }

    private String getAvatarPath(MultipartFile file) {
        String filename = file.getOriginalFilename();
        return getUser().getPhone()+"/"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+filename.substring(filename.lastIndexOf("."));
    }

}
