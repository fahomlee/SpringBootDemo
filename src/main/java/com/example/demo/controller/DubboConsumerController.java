package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
 * import org.springframework.web.bind.annotation.GetMapping; import com.alibaba.dubbo.config.annotation.Reference;
 * import com.example.service.RemoteUserService; import io.swagger.annotations.ApiOperation;
 */
import io.swagger.annotations.Api;

/**
 * 注：消费者需要配置在其他项目中，此处仅提供一个调用例子
 *
 */
@RestController
@Api("模拟服务消费者Controller")
@RequestMapping("/consumerDemo")
public class DubboConsumerController {
     //@Reference配置url可以用于dubbo服务的点对点直连测试
    /*
     * @Reference(url="dubbo://192.168.152.201:20880",version = "1.0.0") RemoteUserService remoteUserService;
     * 
     * @GetMapping("/getRemoteUser")
     * 
     * @ApiOperation(value = "dubbo调用", notes = "dubbo调用") public String getRemoteUser() { return
     * remoteUserService.getUserName(); }
     */


}
