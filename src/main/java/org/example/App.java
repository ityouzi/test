package org.example;

/**
 * Hello world!
 *
 */


import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.cglib.beans.BeanCopier;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * @author: lz
 * @Description:
 *
 * @Date 2021/4/16-17:55
 * @return
 */
@Slf4j
public class App 
{


    public static Date getCurrentTime(){
        return new Date();
    }

    public static String getStringTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    public static Date stringToDate(String time) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
        return date;
    }


    /**
     * noneMatch 全部不匹配
     * 必须全部符合筛选条件
     */
    public static void noneMatch(){
        // false
        List<Integer> integerList1 = Arrays.asList(1,2,3,4,5,6);
        // true
        List<Integer> integerList2 = Arrays.asList(1,2,3,3,3,3);
        boolean b = integerList1.stream().noneMatch(i -> i > 3);
        System.out.println(b);
    }

    /**
     * allMatch匹配所有
     * 必须全部符合筛选条件
     */
    public static void allMatch(){
        // false
        List<Integer> integerList1 = Arrays.asList(1,2,3,4,5,6);
        // true
        List<Integer> integerList2 = Arrays.asList(4,4,4,4,4,4);
        boolean b = integerList1.stream().allMatch(i -> i > 3);
        System.out.println(b);
    }
    
    /**
     * anyMatch匹配其中一个
     * 有一个符合筛选条件return = true
     */
    public static void anyMatch(){
        // true
        List<Integer> integerList = Arrays.asList(1,2,3,4,5,6);
        boolean b = integerList.stream().anyMatch(i -> i > 3);
        System.out.println(b);
    }

    /**
     * flatMap 流转换
     * 将一个流中的每一个值都转换为另一个流
     * map(w \-> w.split(" "))的返回值为Stream<String[]>，我们想获取Stream<String>，
     * 可以通过flatMap方法完成Stream<String[]> \->Stream<String>的转换
     */
    public static void flatMap(){
        List<String> wordList = Arrays.asList("Hello", "World");
        List<String> strList = wordList.stream()
                .map(w -> w.split(" "))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(strList.toString());
    }
        
    /**
     * 统计流中元素个数 count & counting
     */
    public static void count(){
        // count
        List<Integer> integerList1 = Arrays.asList(1, 2, 3, 4, 5,6);
        long count = integerList1.stream().count();
        System.out.println(count);

        // counting
        List<Integer> integerList2 = Arrays.asList(1, 2, 3, 4, 5,6);
        long counting = integerList2.stream().collect(counting());
        System.out.println(counting);
    }

    /**
     * 查找 findFirst
     * 通过findFirst方法查找到第一个大于三的元素并打印
     */
    public static void findFirst(){
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5,6);
        Optional<Integer> first = integerList.stream().filter(i -> i > 3).findFirst();
        Integer integer = first.get();
        System.out.println(integer);
    }

    /**
     * findAny随机查找一个
     * 通过findAny方法查找到其中一个大于三的元素并打印，因为内部进行优化的原因，当找到第一个满足大于三的元素时就结束，
     * 该方法结果和findFirst方法结果一样。
     * 提供findAny方法是为了更好的利用并行流，findFirst方法在并行上限制更多【本篇文章将不介绍并行流】
     */
    public static void findAny(){
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5,6);
        Optional<Integer> any = integerList.stream().filter(i -> i > 3).findAny();
        Integer integer = any.get();
        System.out.println(integer);
    }
    
    /**
     * reduce将流中的元素组合起来
     */
    public static void reduce(){
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5,6);
        int sum = 0;
        Integer reduce = integerList.stream().reduce(0, Integer::sum);
        System.out.println(reduce);
    }
    
    /**
     * 获取流中最小最大值 min - max
     */
    public static void min_max1(){
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5,6);
        OptionalInt min = integerList.stream().mapToInt(Integer::intValue).min();
        OptionalInt max = integerList.stream().mapToInt(Integer::intValue).max();
        System.out.println(min);
        System.out.println(max);
    }
    public static void min_max2(){
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5,6);
        Optional<Integer> collect1 = integerList.stream().map(Integer::intValue).collect(minBy(Integer::compareTo));
        Optional<Integer> collect2 = integerList.stream().map(Integer::intValue).collect(maxBy(Integer::compareTo));
        Integer min = collect1.get();
        Integer max = collect2.get();
        System.out.println(min);
        System.out.println(max);
    }
    public static void min_max3(){
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5,6);
        Optional<Integer> reduce1 = integerList.stream().map(Integer::intValue).reduce(Integer::min);
        Optional<Integer> reduce2 = integerList.stream().map(Integer::intValue).reduce(Integer::max);
        Integer min = reduce1.get();
        Integer max = reduce2.get();
        System.out.println(min);
        System.out.println(max);
    }

    /**
     * summingInt 求和  出错
     */
//    public static void summingInt(){
//        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5,6);
//        int sum = integerList.stream().collect(summingInt(Integer::intValue));
//        System.out.println(sum);
//    }
    public static void sum_reduce(){
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5,6);
        int sum=0;
        Integer reduce = integerList.stream().map(Integer::intValue).reduce(0, Integer::sum);
        System.out.println(reduce);
    }

    /**
     * 求和
     */
    public static void sum(){
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5,6);
        Integer sum = integerList.stream().mapToInt(Integer::intValue).sum();
        System.out.println(sum);
    }
    
    /**
     * 取集合对象的前几条数据
     */
    public static List<Integer> getDateList(){
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> collect = integers.stream().limit(5).collect(toList());
        return collect;
    }
    
    /**
     * 通过averagingInt求平均值 错误
     */
//    public static void averagingInt(){
//        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5,6);
//        double sum = integerList.stream().collect(averagingInt(Integer::intValue));
//        System.out.println(sum);
//    }

    /**
     * foreach 遍历
     */
    public static void foreach(){
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5,6);
        integerList.stream().forEach(System.out::println);
    }



    /***
     * 获取指定开始及结束日期的所有日期
     *
     * @param paramString1
     *            开始日期 格式如:yyyy-MM-dd
     * @param paramString2
     *            结束日期 格式如:yyyy-MM-dd
     * @return
     */
    public static List<String> getAllDatesByDate(Date paramString1, Date paramString2) {
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList localArrayList = new ArrayList();
        Date localDate1 = paramString1;
        Date localDate2 = paramString2;
        Calendar localCalendar = Calendar.getInstance();
        while (localDate1.compareTo(localDate2) <= 0) {
            localCalendar.setTime(localDate1);

            String str = localSimpleDateFormat.format(localDate1);
            localArrayList.add(str);
            localCalendar.add(5, 1);
            localDate1 = localCalendar.getTime();
        }
        return localArrayList;
    }


    public static Stu cc(){
        Student student = new Student();
        Stu stu = new Stu();
        student.setName("路上只是");
        student.setAge(18);
        student.setClassNmae("傻瓜");

        BeanCopier beanCopier = BeanCopier.create(Student.class, Stu.class, false);
        beanCopier.copy(student, stu,null );
        return stu;
    }

    /**
     * 在项目中使用stream的filter对list里的对象属性值判断时，如果对象属性值有null会
     * 报空指针异常。（如上文中的Stu对象的age属性出现null值）。
     * 解决方法：对此属性先做非空判断，注意：非空判断放前面
     */
    public static List<Student> test11(){
        Student student1 = new Student(1,"张一","一班",null);
        Student student2 = new Student(2,"张二","一班",null);
        Student student3 = new Student(3,null,"一班",null);
        Student student4 = new Student(4,"张四","一班",null);
        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student2);
        list.add(student3);
        list.add(student4);
        List<Student> collect = list.stream().filter(student -> student.getName() != null).collect(toList());
        return collect;
    }

    public static Object tt(){
        Student student1 = new Student(1,"张一","一班",null);
        Student student2 = new Student(2,"张二","一班",null);
        Student student3 = new Student(3,null,"一班",null);
        Student student4 = new Student(4,"张四","一班",null);
        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student2);
        list.add(student3);
        list.add(student4);

        list.forEach(student -> {
            System.out.println("1");
            if ("3".equals(student.getAge().toString())){
                System.out.println("进来了");
                return;
            }
            System.out.println(student);
        });



        return null;
    }

    /**
     * ambda表达式替换list中某个实体类的某个元素值
     */
    public static void yy(){
        Student stu1 = new Student(18, "张三1","二班",null);
        Student stu2 = new Student(19, "张三2","二班",null);
        Student stu3 = new Student(15, "张三3","二班",null);
        Student stu4 = new Student(17, "张三4","二班",null);
        List<Student> list = new ArrayList<>();
        list.add(stu1);
        list.add(stu2);
        list.add(stu3);
        list.add(stu4);
        List<Student> collect = list.stream().peek(student -> {
            student.setAge(15);
            student.setClassNmae("四班");
        }).collect(toList());
        System.out.println(JSON.toJSONString(collect));
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static final String datePath()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    public static void yy1(){
        Student stu1 = new Student(18, "张三1","二班",null);
        Student stu2 = new Student(19, "张三2","二班",null);
        Student stu3 = new Student(15, "张三3","二班",null);
        Student stu4 = new Student(17, "张三4","二班",null);
        List<Student> list = Lists.newArrayList(stu1,stu2,stu3,stu4);
        List<Student> collect = list.stream().peek(student -> {
            student.setAge(15);
            student.setTime(new Date());
        }).collect(toList());
        System.out.println(JSON.toJSONString(collect));
    }

    public static void yy2(){
        Integer sta = 18;
        Student stu1 = new Student(18, "张三1","二班",null);
        Student stu2 = new Student(18, "张三2","二班",null);
        Student stu3 = new Student(18, "张三3","二班",null);
        Student stu4 = new Student(18, "张三4","二班",null);
        List<Student> list = Lists.newArrayList(stu1,stu2,stu3,stu4);
        boolean result = list.stream().allMatch(record -> sta.equals(record.getAge()));
        System.out.println(result);

    }

    
    /**
     * List集合拼接成以逗号分隔的字符串
     */
    public static void testList(){
        // 如何把list集合拼接成以逗号分隔的字符串 a,b,c
        List<String> list = Arrays.asList("a","b","c");

        // 第一种方法，可以用stream流
        String collect = list.stream().collect(joining(","));
        System.out.println(collect);

        // 第二种方法，其实String也有join方法可以实现这个功能
        String join = String.join(",", list);
        System.out.println(join);
    }

    /**
     *  两个List集合取交集
     */
    public static void testList2(){
        List<String> list1 = Arrays.asList("a","b","c");
        List<String> list2 = Arrays.asList("a","b","d");
        list1.retainAll(list2);
        System.out.println(list1);
    }

    /**
     * 集合依据对象的某个属性去重
     */
    public static void testList3(){
        Student stu1 = new Student(18, "张三1","二班",null);
        Student stu2 = new Student(18, "张三1","二班",null);
        Student stu3 = new Student(18, "张三1","二班",null);
        Student stu4 = new Student(18, "张三1","二班",null);

        List<Student> list = Lists.newArrayList(stu1,stu2,stu3,stu4);

        // 去重
        ArrayList<Student> collect = list.stream().collect(
                collectingAndThen(toCollection(() -> new TreeSet<>(Comparator.comparing(Student::getAge))), ArrayList::new));
        System.out.println(collect);
    }

    /**
     * 集合时间排序
     */
    public static void testList4(){
        Student stu1 = new Student(18, "张三1","二班",null);
        Student stu2 = new Student(19, "张三2","二班",null);
        Student stu3 = new Student(15, "张三3","二班",null);
        Student stu4 = new Student(17, "张三4","二班",null);
        List<Student> list = Lists.newArrayList(stu1,stu2,stu3,stu4);
        // 升序
        List<Student> collect2 = list.stream().sorted(Comparator.comparing(Student::getAge, Comparator.naturalOrder())).collect(toList());
        List<Student> collect3 = list.stream().sorted(Comparator.comparing(Student::getAge)).collect(toList());
        System.out.println(collect2);
        System.out.println(collect3);

        // 倒叙
        List<Student> collect4 = list.stream().sorted(Comparator.comparing(Student::getAge, Comparator.reverseOrder())).collect(toList());
        List<Student> collect1 = list.stream().sorted(Comparator.comparing(Student::getAge).reversed()).collect(toList());
        System.out.println(collect4);
        System.out.println(collect1);
    }

    public static boolean instance(){
        Integer i = 0;
        if (i instanceof Integer){
            log.info("result={}",true);
            return true;
        }
        log.info("result={}",false);
        return false;
    }

    public static void isTrue(){
        boolean result = true;
        String a;
        a = result ? "1" : "2";
        log.info("a={}", a);
    }
    
    /**
     * 判断对象是否有重复
     */
    public static boolean varifyObj(){
        List<Integer> integers = Arrays.asList(1, 2, 3, 3, 5);
        long count = integers.stream().distinct().count();
        if (count == integers.size()){
            return true;
        }
        return false;
    }

    /**
     * Excel
     */
    public static List<Student> createModelList(){
        List<Student> studentList = new ArrayList<>();
        for (int i = 0;i < 50;i++){
            Student student = Student.builder()
                    .age(18)
                    .name("张三")
                    .classNmae("二班")
                    .time(new Date())
                    .build();
            studentList.add(student);
        }
        return studentList;
    }

    public static List<Student> testList22(){
        Student stu1 = new Student(18, "张三1","二班",null);
        Student stu2 = new Student(19, "张三2","二班",null);
        Student stu3 = new Student(15, "张三3","二班",null);
        Student stu4 = new Student(17, "张三4","二班",null);
        return Lists.newArrayList(stu1,stu2,stu3,stu4);
    }


    public static void main(String[] args) throws IOException {

//        App.noneMatch();
//        App.allMatch();
//        App.anyMatch();
//        App.flatMap();
//        App.count();
//        App.count();
//        App.findFirst();
//        App.findAny();
//        App.reduce();
//        App.summingInt();
//        App.sum_reduce();
//        App.sum();
//        App.foreach();

//        App.sum();



//        App.testList4();
//        App.yy2();
//        App.testList3();

//        App.isTrue();

//        List<Integer> dateList = App.getDateList();
//        System.out.println(JSON.toJSONString(dateList));

        System.out.println(varifyObj());


//
//        for (int i=0; i<6; i++){
//            Student student = testList22().get(i);
//            if (student == null){
//                System.out.println("1111111111111");
//            }
//        }


    }


}
