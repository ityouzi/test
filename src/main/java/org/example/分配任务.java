package org.example;

import com.google.common.base.Stopwatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lizhen created on 2021-09-11 16:47
 */
public class 分配任务 {

    /**
     * 依据线程数量合理分配线程任务
     *
     * @param taskList          任务
     * @param threadCount       线程数
     * @author: lizhen
     * @date: 2021/9/12-19:08
     * @return T
     */
    public static <T> Map<Object, Object> distributeTasks(List<T> taskList, int threadCount){

        // 每个线程至少执行的任务数
        int minTaskCount = taskList.size() / threadCount;

        // 平均分配后还剩余的任务数
        int remainTaskCount = taskList.size() % threadCount;

        // 实际启动的线程数actualThreadCount，如果实际工作的线程大于工作任务数，则启动任务数个线程即可
        int actualThreadCount = minTaskCount > 0 ? threadCount : remainTaskCount;

        // 要启动的线程数组，以及每个线程要执行的任务列表
        Map<Object, Object> map = new HashMap<>(actualThreadCount);

        final AtomicInteger[] taskIndex = {new AtomicInteger()};

        // 任务剩余数重新声明标志，防止在执行过程中改变剩余线程数remainTaskCount的值
        final int[] remainIndces = {remainTaskCount};

        for (int i = 0; i < actualThreadCount; i++){
            List<T> list = new ArrayList<>();
            // 如果每个线程至少要执行的任务数>0，线程需要分配到基本任务
            if (minTaskCount > 0){
                for (int j = taskIndex[0].get(); j < minTaskCount + taskIndex[0].get(); j++){
                    list.add(taskList.get(j));
                }
            }
            taskIndex[0].addAndGet(minTaskCount);

            // 还有剩余的任务，则补充一个到这个线程中
            if (remainIndces[0] > 0) {
                list.add(taskList.get(taskIndex[0].getAndIncrement()));
                remainIndces[0]--;
            }
            map.put(i, list);
        }
        return map;
    }

    public static void main(String[] args) {
        List<String> all = new ArrayList<>();
        Stopwatch sotp1 = Stopwatch.createStarted();
        for (int i = 0; i < 10000; i++){
            all.add(String.valueOf(i));
        }
        System.out.println("构建任务对象耗时：" + sotp1.stop());

        // 设定工作的线程数为5
        int threadNum = 1000;

        Stopwatch stop2 = Stopwatch.createStarted();
        Map<Object, Object> objectObjectMap = 分配任务.distributeTasks(all, threadNum);
        System.out.println("最终分配的任务集合数量：" + objectObjectMap.size());

        System.out.println("分配任务耗时：" + stop2.stop());





    }
}
