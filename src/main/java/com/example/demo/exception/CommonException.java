package com.example.demo.exception;

/**
 * 统一异常实体类
 * 
 * @author fahomlee
 *
 */
public class CommonException extends Exception {

	private static final long serialVersionUID = 1L;
	private String code="1111";
	private String msg="统一异常";

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
