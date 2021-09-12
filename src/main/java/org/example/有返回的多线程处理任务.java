package org.example;

import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author lizhen created on 2021-08-23 15:19
 */
@Slf4j
@Service
public class 有返回的多线程处理任务 {

    @Autowired
    private StudentService studentService;

    /** 测试服cup 10个，这里扩一倍*/
    public static int threadSize = 8;
    public static ExecutorService pool = Executors.newFixedThreadPool(threadSize);






    // 多线程执行，不在事务中执行
    public List<Student> getAll(List<Integer> classIds) throws ExecutionException, InterruptedException {
        // 分配线程任务
        List<Future<List<Student>>> list = new ArrayList<>();
        for (int i = 0; i<threadSize; i++){
            List<Integer> allocate = 分配线程任务方法.allocate(i, classIds, threadSize);
            log.info("当前线程分配的任务 tasks={}", allocate);
            if (CollectionUtil.isEmpty(allocate)){
                break;
            }
            Callable<List<Student>> callable = new myCallable(allocate);
            Future<List<Student>> submit = pool.submit(callable);
            list.add(submit);
        }
        if (CollectionUtil.isEmpty(list)){
            return CollectionUtil.newArrayList();
        }

        List<Student> allList = new ArrayList<>();
        for (Future<List<Student>> future : list) {
            List<Student> studentList = future.get();
            allList.addAll(studentList);
        }

        return allList;

    }

    /**
     * 内部类
     */
    class myCallable implements Callable<List<Student>>{

        private List<Integer> classIds;

        myCallable(List<Integer> classIds){
            this.classIds = classIds;
        }

        @Override
        public List<Student> call() {
            List<Student> all = studentService.getAll(classIds);
            return all;
        }
    }


}
