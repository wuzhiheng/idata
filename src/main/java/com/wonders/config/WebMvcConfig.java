package com.wonders.config;

import com.wonders.properties.IDataProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 1:36 下午 2020/4/5
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private IDataProperties iDataProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        File file = new File(iDataProperties.getFile().getAvatarLocation());
        if(!file.exists()){
            file.mkdirs();
        }
        registry.addResourceHandler(iDataProperties.getFile().getAvatarPath()+"/**")
                .addResourceLocations("file:"+file.getAbsolutePath()+"/");
    }
}
