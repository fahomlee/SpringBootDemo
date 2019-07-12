package com.example.demo.retry;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;


/**
 * 重试handle
 * @author fahomlee
 *
 */
@Service
@EnableRetry
@Slf4j
public class RetryHandle {


    /**
     * value 抛该异常时重试，具体使用时可定义具体要重试的异常
     * maxAttempts  最大重试次数，默认3次
     * delay重试延迟时间ms  multiplier 重试倍数，下一次是上一次的几倍  5  10 20
     */
    @Retryable(value = {Exception.class},maxAttempts = 3, backoff = @Backoff(delay = 5 * 1000, multiplier = 2))
    public String retry(boolean retryFlag)  {
        log.info("************执行重试*****************");
        if(retryFlag) {
            throw new RuntimeException("retry...");
        }
        return null;
    }


}
