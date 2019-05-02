package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.CommonException;

@RestController
@RequestMapping("/exception")
public class ExceptionDemoController {

	/**
	 * 测试CommonException
	 * 
	 * @return
	 * @throws CommonException
	 */
	@RequestMapping("/demo1")
	public String testCommonException() throws CommonException {
		int a=0;
		try {
			 a = 1 / 0;
		} catch (Exception e) {
			throw new CommonException();
		}
		return ""+a;
	}

	/**
	 * 测试Exception
	 * 
	 * @return
	 * @throws CommonException
	 */
	@RequestMapping("/demo2")
	public String testException() {
		int a=1 / 0;
		return ""+a;
	}
}
