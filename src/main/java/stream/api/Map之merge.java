package stream.api;

import com.alibaba.fastjson.JSON;
import stream.pojo.StudentScore;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * 计算学生总成绩
 *
 * @author lizhen created on 2021-09-22 18:37
 * @version 1.0
 */
public class Map之merge {

    public static void main(String[] args) {

        StudentScore studentScore1 = new StudentScore("张三", "语文", 80);
        StudentScore studentScore2 = new StudentScore("张三", "英语", 90);
        StudentScore studentScore3 = new StudentScore("张三", "数学", 80);
        StudentScore studentScore4 = new StudentScore("李四", "语文", 80);
        StudentScore studentScore5 = new StudentScore("李四", "英语", 60);
        StudentScore studentScore6 = new StudentScore("李四", "数学", 60);

        List<StudentScore> scoreList = Arrays.asList(studentScore1, studentScore2, studentScore3, studentScore4, studentScore5, studentScore6);

        Map<String, Integer> scoreMap = new HashMap<>(scoreList.size());
        scoreList.forEach(s -> scoreMap.merge(
                s.getName(),
                s.getScore(),
                Integer::sum
        ));
        System.out.println(JSON.toJSONString(scoreMap));

        // 总人数
        Map<String, List<StudentScore>> stringListMap = scoreList.stream().collect(groupingBy(StudentScore::getName));
        int size = stringListMap.size();
        // 叫张三的人数
        int count = stringListMap.get("张三").stream().collect(groupingBy(StudentScore::getName)).size();
        System.out.println(size);
        System.out.println(count);

        // 总数，最小值，平均值，最大值
        Map<String, IntSummaryStatistics> collect = scoreList.stream().collect(groupingBy(StudentScore::getName, Collectors.summarizingInt(StudentScore::getScore)));
        System.out.println(collect.get("张三"));

        // 依据名字分组，并计算总成绩
        Map<String, Integer> collect1 = scoreList.stream().collect(groupingBy(StudentScore::getName, Collectors.summingInt(StudentScore::getScore)));
        System.out.println(collect1);


    }
}
