package org.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author lizhen created on 2021-08-23 15:20
 */
@Slf4j
@Service
public class StudentServiceImpl implements StudentService {



    @Override
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
