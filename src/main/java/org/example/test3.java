package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author lizhen created on 2021-08-18 15:01
 */
public class test3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService pool = Executors.newFixedThreadPool(5);

        // 创建多个有返回值得任务
        List<Future> list = new ArrayList<>();
        for (int i=0; i<5; i++){
            Callable callable = new myCallable(i + " ");

            // 执行任务并获取future对象
            Future submit = pool.submit(callable);
            list.add(submit);
        }
        pool.shutdown();

        // 获取所有并发任务的运行结果
        for (Future future : list){
            // 从Future对象上获取任务的返回值，并输出到控制台
            System.out.println(">>>" + future.get().toString());
        }

    }
}

class myCallable implements Callable<List<String>>{
    private String taskNum;

    myCallable(String taskNum){
        this.taskNum = taskNum;
    }

    @Override
    public List<String> call() throws Exception {
        List<String> a = new ArrayList<>();
        for (int i=0; i<2; i++){
            String b = "123456";
            a.add(b);
        }
        System.out.println(">>>" + taskNum + "任务终止");
        return a;

    }
}




