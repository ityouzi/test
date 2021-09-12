package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author lizhen created on 2021-05-29 18:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stu {

    private String age;
    private String name;
    private String classNmae;
    private String classes;
    private Date time;

}
