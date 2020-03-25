package com.wonders.vo;

import com.wonders.util.IConstant;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 通过返回一般Ajax请求返回的结果
 * 创建日期：2017-12-22上午12:11:03
 * author:wuzhiheng
 */
@Data
@Accessors(chain = true)
public class ReturnMsg implements Serializable{

	private String code;

	private String msg;

	private Object data;

	public ReturnMsg(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public ReturnMsg(String code) {
		super();
		this.code = code;
	}

	public ReturnMsg(String code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}


	public static ReturnMsg successTip(){
		return new ReturnMsg(IConstant.CODE_SUCCESS, IConstant.MSG_SUCCESS);
	}

	public static ReturnMsg successTip(Object object){
		return new ReturnMsg(IConstant.CODE_SUCCESS, IConstant.MSG_SUCCESS,object);
	}

	public static ReturnMsg errorTip(){
		return new ReturnMsg(IConstant.CODE_ERROR, IConstant.MSG_ERROR);
	}

	public static ReturnMsg errorTip(String msg){
		return new ReturnMsg(IConstant.CODE_ERROR, msg);
	}



}
