package org.example;

import java.util.List;

/**
 * 来定义学生的工作线程，由该线程来执行任务
 *
 * @author lizhen created on 2021-09-11 15:46
 */
public class StudentWorkThread extends Thread{
    private List<Task> taskList = null;
    private int threadId;

    public StudentWorkThread(List<Task> taskList, int threadId) {
        this.taskList = taskList;
        this.threadId = threadId;
    }

    /** 执行被指派的任务 */
    @Override
    public void run() {
        for (Task task : taskList) {
            task.execute();
        }
    }

}
