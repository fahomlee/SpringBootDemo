package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 异常处理器
 * @author fahomlee
 *
 */
@ControllerAdvice
public class CommonExceptionHandler {
 
    /**
     *  拦截Exception类的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String,Object> exceptionHandler(Exception e){
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("respCode", "9999");
        result.put("respMsg", e.getMessage());
        //正常开发中，可创建一个统一响应实体，如CommonResp
        return result; 
    }
    /**
     * 拦截 CommonException 的异常
     * @param ex
     * @return
     */
    @ExceptionHandler(CommonException.class)
    @ResponseBody
    public Map<String,Object> exceptionHandler(CommonException ex){
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("respCode", ex.getCode());
        result.put("respMsg", ex.getMsg());
        return result; 
    }
}
