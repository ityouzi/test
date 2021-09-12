package org.example;

import com.google.common.base.Stopwatch;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Java多线程任务分发
 *
 * @author lizhen created on 2021-09-11 15:36
 * @version 4.0
 */
public class JavaTask {


    /**
     * （1）待执行的任务列表是什么？
     * （2）需要启动多少Java线程去执行任务？
     * （3）每个Java线程实际要执行多少任务？（or：任务如何分发给每个Java线程？）
     *
     *
     * （1）任务数为taskNum
     * （2）计划启动线程数为planThreadNum
     * （3）实际启动线程数为realThreadNum
     *
     *
     * （1）确认实际启动的线程数：
     *  如果taskNum < planThreadNum，则实际启动taskNum个线程，即一个线程执行一个任务；
     *  如果taskNum众多，则实际启动planThreadNum个线程即可。
     *
     * （2）确定每个线程执行的任务数：
     *  如果taskNum < planThreadNum，每个线程都只执行一个任务；
     *  如果taskNum众多，则先将taskNum按planThreadNum均分，余下的任务从前至后依次附加到线程中即可。
     *
     *
     *
     *
     * （1）我们需要一个Task类，来定义任务的状态（就绪、执行中、结束）和任务的行为（执行）
     * （2）我们需要一个Teacher类，来执行任务分配的工作
     * （3）我们需要一个StudentWorkThread类，来定义学生的工作线程，由该线程来执行任务
     * （4）我们需要一个DoTeacherWork的测试类，用来测试题引中的场景
     */


    /** 任务分配 */
    public static List[] distributeTasks(List taskList, int threadCount){
        // 每个线程至少执行的任务数
        int minTaskCount = taskList.size() / threadCount;

        // 平均分配后还剩余的任务数
        int remainTaskCount = taskList.size() % threadCount;

        // 实际启动的线程数，如果实际工作的线程大于工作任务数，则启动任务数个线程即可
        int actualThreadCount = minTaskCount > 0 ? threadCount : remainTaskCount;

        // 要启动的线程数组，以及每个线程要执行的任务列表
        List[] perThreadWorkList = new List[actualThreadCount];

        final AtomicInteger[] taskIndex = {new AtomicInteger()};

        // 任务剩余数重新声明标志，防止在执行过程中改变剩余线程数remainTaskCount的值
        final int[] remainIndces = {remainTaskCount};

        for (int i = 0; i < perThreadWorkList.length; i++){
            perThreadWorkList[i] = new ArrayList();

            // 如果每个线程至少要执行的任务数>0，线程需要分配到基本任务
            if (minTaskCount > 0){
                for (int j = taskIndex[0].get(); j < minTaskCount + taskIndex[0].get(); j++){
                    perThreadWorkList[i].add(taskList.get(j));
                }
            }
            taskIndex[0].addAndGet(minTaskCount);

            // 还有剩余的任务，则补充一个到这个线程中
            if (remainIndces[0] > 0) {
                perThreadWorkList[i].add(taskList.get(taskIndex[0].getAndIncrement()));
                remainIndces[0]--;
            }
        }

        // 打印任务分配情况
//        for (int i = 0; i < perThreadWorkList.length; i++){
//            System.out.println("线程" + i + "任务数: " + perThreadWorkList[i].size() +
//                    ",区间[" + ((Task) perThreadWorkList[i].get(0)).getTaskId() + "," +
//                    ((Task) perThreadWorkList[i].get(perThreadWorkList[i].size() - 1)).getTaskId() + "]");
//        }
        return perThreadWorkList;
    }





    public static void main(String[] args) {


        // 构建老师的任务列表
        List<Task> taskList = new ArrayList<Task>();
        for (int i = 0; i < 100; i++) {
            taskList.add(new Task(i));
        }

        System.out.println("老师的任务列表数：" + taskList.size());

        // 设定学生工作的线程数为5
        int threadNum = 10;

        Stopwatch stop = Stopwatch.createStarted();
        List[] perThreadWorkList = JavaTask.distributeTasks(taskList, threadNum);
        System.out.println("分配任务耗时：" + stop.stop());

        System.out.println("实际要启动的工作线程数：" + perThreadWorkList.length);

        Stopwatch stop1 = Stopwatch.createStarted();
        for (int i = 0; i < perThreadWorkList.length; i++) {
            StudentWorkThread studentWorkThread = new StudentWorkThread(perThreadWorkList[i], i);
            studentWorkThread.start();
        }
        System.out.println("1耗时：" + stop1.stop());

        List<Integer> asList = Arrays.asList(perThreadWorkList.length);


//        Stopwatch stops = Stopwatch.createStarted();
//        asList.parallelStream().forEach(a ->{
//            StudentWorkThread studentWorkThread = new StudentWorkThread(perThreadWorkList[a-1], a);
//            studentWorkThread.start();
//        });
//        System.out.println("2耗时：" + stops.stop());

//        Stopwatch sotp2 = Stopwatch.createStarted();
//        asList.stream().parallel().forEach(a ->{
//            StudentWorkThread studentWorkThread = new StudentWorkThread(perThreadWorkList[a-1], a);
//            studentWorkThread.start();
//        });
//        System.out.println("3耗时：" + sotp2.stop());

//        Stopwatch sotp3 = Stopwatch.createStarted();
//        asList.forEach(a ->{
//            StudentWorkThread studentWorkThread = new StudentWorkThread(perThreadWorkList[a-1], a);
//            studentWorkThread.start();
//        });
//        System.out.println("4耗时：" + sotp3.stop());



    }
}
