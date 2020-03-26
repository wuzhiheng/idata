package com.wonders.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 创建日期：2017-12-15下午12:46:31
 * author:wuzhiheng
 */
public class IConstant {

	//定义返回约定好的信息
	public final static String CODE_SUCCESS = "200";
	public final static String CODE_ERROR = "404";
	public final static String CODE_UNKNOW = "500";
	public final static String MSG_SUCCESS = "操作成功！";
	public final static String MSG_ERROR = "操作失败！";
	public final static String MSG_UNKONW = "未知错误！";

}
