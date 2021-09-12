package org.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author lizhen created on 2021-08-23 15:46
 */
@Slf4j
@Component
public class 执行任务Callable implements Callable<List<Student>> {


    @Autowired
    private StudentService studentService;



    private List<Integer> classIds;

    执行任务Callable(List<Integer> classIds){
        this.classIds = classIds;
    }

    @Override
    public List<Student> call() {
//        List<Student> all = getAll(classIds);
        List<Student> all = studentService.getAll(classIds);
        return all;
    }


    public List<Student> getAll(List<Integer> classIds) {
        log.info("执行了查询班级学生的方法");
        Student stu1 = new Student(19, "张三1","二班",new Date());
        Student stu2 = new Student(18, "张三2","二班",new Date());
        Student stu3 = new Student(15, "张三3","二班",new Date());
        Student stu4 = new Student(17, "张三4","二班",new Date());
        List<Student> studentList = Arrays.asList(stu1, stu2, stu3, stu4);
        return studentList;
    }
}
