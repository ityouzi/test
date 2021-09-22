package stream.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学生成绩
 *
 * @author lizhen created on 2021-09-22 18:39
 * @version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentScore {

    /** 姓名 */
    private String name;

    /** 学科 */
    private String subject;

    /** 成绩 */
    private Integer score;
}
