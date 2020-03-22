package com.wonders.config;

import com.wonders.filter.XssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
/**
 * @Author : wuzhiheng
 * @Description : 配置xss过滤
 * @Date Created in 11:24 上午 2020/3/16
 */
@Configuration
public class XssConfig {

	/**
	 * xss过滤拦截器
	 */
	@Bean
	public FilterRegistrationBean xssFilterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new XssFilter());
		filterRegistrationBean.setOrder(1);
//		filterRegistrationBean.setEnabled(false);
		filterRegistrationBean.addUrlPatterns("/*");
		Map<String, String> initParameters = new HashMap<>();
		initParameters.put("excludes", "/favicon.ico,/images/*,/js/*,/css/*");
		//针对富文本是否过滤，true | false 当为false且写到参数为content或者withHtml时，不过滤参数
		initParameters.put("isIncludeRichText", "false");
		filterRegistrationBean.setInitParameters(initParameters);
		return filterRegistrationBean;
	}
}
