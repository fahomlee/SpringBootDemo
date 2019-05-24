package com.example.demo.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时器
 *
 */
@Component
public class TaskDemo1 {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    // 定义每过1秒执行一次任务0/1 * * * * ?
    //@Scheduled(cron = "0 0/1 * * * ? ")
    public void reportCurrentTime() {
        System.out.println("现在时间：" + dateFormat.format(new Date()));
    }
}
