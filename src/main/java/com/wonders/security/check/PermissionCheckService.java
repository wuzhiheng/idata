package com.wonders.security.check;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @Author : wuzhiheng
 * @Description : 权限判断
 * @Date Created in 5:11 下午 2020/3/26
 */
@Service
public class PermissionCheckService {
    public boolean check(Authentication authentication) {
        return true;
    }
}
