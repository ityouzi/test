package org.example;

import java.util.List;

/**
 * @author lizhen created on 2021-08-23 15:20
 */
public interface StudentService {

    List<Student> getAll(List<Integer> classIds);
}
