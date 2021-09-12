package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lizhen created on 2021-08-23 15:08
 */
public class 分配线程任务方法 {

    public static void main(String[] args) {
        int threadSize = 5;
        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i<13; i++){
            taskList.add(new Task(i));
        }
        for (int i = 0; i<threadSize; i++){
            List<Task> allocate = allocate(i, taskList, threadSize);
            System.out.println(allocate);
            System.out.println("=====");
        }
    }



    /**
     * 分配任务
     *
     * @param curThreadIndex 线程索引
     * @param taskAll 被分配对象
     * @param threadSize 线程数量
     * @author lizhen
     * @date 2021/8/23-15:08
     */
    public static <T> List<T> allocate(int curThreadIndex, List<T> taskAll, int threadSize){
        List<T> result = new ArrayList<>();
        for (int i = 0; i < taskAll.size(); i++){
            if (i % threadSize == curThreadIndex){
                result.add(taskAll.get(i));
            }
        }
        return result;
    }

    private static class Task{
        private Integer idx;
        private Task(Integer idx){this.idx = idx;}

        @Override
        public String toString() {
            return "Task{" +
                    "idx=" + idx +
                    '}';
        }
    }
}
