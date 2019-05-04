package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 异常处理器
 * 
 * @author fahomlee
 *
 */
@ControllerAdvice
public class CommonExceptionHandler {
	private final static String DEFAULT_ERROR_VIEW = "exception";// 错误信息页
	@Autowired
	private ExceptionInfoBuilder exceptionInfoBuilder;//异常信息包装类

	/**
	 * 拦截Exception类的异常（统一异常）
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Object exceptionHandler(HttpServletRequest request, Exception e) {
		//1、若为AJAX请求,则返回异常信息(JSON)
		if (isAjaxRequest(request)) {
			return exceptionInfoBuilder.getExceptionInfo(request,e);
		}
		//2、返回统一异常页面
		//exceptionInfo为exception.html中的对象
		return new ModelAndView(DEFAULT_ERROR_VIEW, "exceptionInfo", exceptionInfoBuilder.getExceptionInfo(request, e));
	}

	/**
	 * 拦截 CommonException 的异常
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(CommonException.class)
	@ResponseBody
	public Map<String, Object> exceptionHandler(CommonException ex) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("respCode", ex.getCode());
		result.put("respMsg", ex.getMsg());
		return result;
	}

	/**
	 * 判断是否是ajax请求
	 * 
	 * @param request
	 * @return
	 */
	private boolean isAjaxRequest(HttpServletRequest request) {
		return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
	}
}
