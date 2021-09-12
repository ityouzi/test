package org.example;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lizhen created on 2021-08-23 14:56
 */
public class 依据多个属性分组 {

    public static void main(String[] args) {

        Student stu1 = new Student(18, "张三1","二班",new Date());
        Student stu2 = new Student(18, "张三2","二班",new Date());
        Student stu3 = new Student(15, "张三3","二班",new Date());
        Student stu4 = new Student(17, "张三4","二班",new Date());
        List<Student> studentList = Arrays.asList(stu1, stu2, stu3, stu4);
        // 单一属性
        if (CollectionUtil.isNotEmpty(studentList)){
            Map<Integer, List<Student>> collect = studentList.stream().collect(Collectors.groupingBy(Student::getAge));
            System.out.println(JSON.toJSONString(collect));
        }
        // 多个属性，会依据条件组成一个key值
        if (CollectionUtil.isNotEmpty(studentList)){
            Map<String, List<Student>> collect = studentList.stream().collect(Collectors.groupingBy(v -> v.getAge() + v.getClassNmae()));
            System.out.println(JSON.toJSONString(collect));
        }


    }


}
