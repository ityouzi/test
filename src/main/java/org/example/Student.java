package org.example;

import com.alibaba.excel.annotation.ExcelProperty;
import com.sun.xml.internal.ws.developer.Serialization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author lizhen created on 2021-04-28 14:29
 * @description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @ExcelProperty(value = "年龄", index = 0)
    private Integer age;

    @ExcelProperty(value = "姓名", index = 1)
    private String name;

    @ExcelProperty(value = "班级", index = 2)
    private String classNmae;

    @ExcelProperty(value = "时间", index = 3)
    private Date time;



}
