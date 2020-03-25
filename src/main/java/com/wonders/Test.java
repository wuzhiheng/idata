package com.wonders;

import org.springframework.util.AntPathMatcher;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 6:29 下午 2020/3/25
 */
public class Test {


    public static void main(String[] args) {
        System.out.println(new AntPathMatcher().match("*.png", "/images/a.png"));
    }
}
