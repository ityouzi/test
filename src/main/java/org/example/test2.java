package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author lizhen created on 2021-08-18 14:43
 */
public class test2 implements Callable<List<String>> {

    @Override
    public List<String> call() throws Exception {
        int aa = 5;
        List<String> a = new ArrayList<>();
        while (aa > 0){

            for (int i=0; i<2; i++){
                String b = "123456";
                a.add(b);
            }
            System.out.println(aa-- + " is saled by "+Thread.currentThread().getName());
        }
        return a;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test2 t = new test2();
        FutureTask<List<String>> task = new FutureTask<>(t);
        Thread thread1 = new Thread(task,"f1");
        Thread thread2 = new Thread(task,"f2");
        Thread thread3 = new Thread(task,"f3");
        thread1.start();
        thread2.start();
        thread3.start();


        List<String> strings = task.get();
        System.out.println(strings);


    }


}
