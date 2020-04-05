package com.wonders.properties;

import lombok.Data;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 1:34 下午 2020/4/5
 */
@Data
public class FileProperties {

    //访问头像路径
    private String avatarPath;
    //头像文件存储位置
    private String avatarLocation;
    //默认的头像图片地址
    private String defaultAvatar;

}
