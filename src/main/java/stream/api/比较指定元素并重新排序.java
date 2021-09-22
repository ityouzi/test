package stream.api;

import com.google.common.collect.Lists;
import org.example.Student;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author lizhen created on 2021-09-15 18:08
 */
public class 比较指定元素并重新排序 {


    public static void main(String[] args) {
//        String a = "1006";
//
//        List<String> strings = new ArrayList<>();
//        strings.add("1001");
//        strings.add("1005");
//        strings.add("1006");
//        strings.add("1009");
//        // List<String> collect = strings.stream().filter(s -> s.equals(a)).sorted().collect(Collectors.toList());
//
//        // 我想输出结果时【1006 在第一个，其他的随便】
//
//
//
//
//
//        Iterator<String> items = strings.iterator();
//        while (items.hasNext()) {
//            String item = items.next();
//            if (item.equals(a)) {
//                strings.remove(item);
//            }
//        }
//        strings.add(0, a);
//
//
//
//        System.out.println(strings);

        Student student1 = new Student(18, "张三", "一班", new Date());
        Student student2 = new Student(19, "张思", "一班", new Date());
        Student student3 = new Student(20, "张柳", "一班", new Date());
        Student student4 = new Student(21, "李四", "一班", new Date());

        List<Student> list = Lists.newArrayList(student1, student2, student3, student4);

        Iterator<Student> itmes = list.iterator();
        Student stu = null;
        while (itmes.hasNext()){
            Student next = itmes.next();
            if (next.getAge().equals(19)){
                stu = next;
                itmes.remove(); // 这里不能用ArrayList的remove() 会计算总数报异常
            }
        }
        list.add(0, stu);
        System.out.println(list);
    }





}
