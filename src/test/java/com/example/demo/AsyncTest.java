package com.example.demo;

import java.util.concurrent.Future;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.async.AsyncTask;

public class AsyncTest extends SpringBootDemoApplicationTests {
    @Autowired
    private AsyncTask asyncTask;

    @Test
    public void testAsyncTask() throws Exception {
        long start = System.currentTimeMillis();
        Future<Boolean> a = asyncTask.doTask11();
        Future<Boolean> b = asyncTask.doTask22();
        Future<Boolean> c = asyncTask.doTask33();
        while (!a.isDone() || !b.isDone() || !c.isDone()) {
            if (a.isDone() && b.isDone() && c.isDone()) {
                break;
            }
        }
        long end = System.currentTimeMillis();
        //同步执行时间为各个任务的时间总和1000+600+700=2300毫秒
        //异步执行时间只要1000+毫秒
        String times = "任务全部完成，总耗时：" + (end - start) + "毫秒";
        System.out.println(times);
    }
}
