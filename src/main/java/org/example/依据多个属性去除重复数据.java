package org.example;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * @author lizhen created on 2021-08-19 16:53
 */
@Slf4j
public class 依据多个属性去除重复数据 {



    public static void main(String[] args) {



    String txt = "[\n" +
            "    {\n" +
            "        \"corpId\":\"ww52e65672fab7c894\",\n" +
            "        \"createTime\":1629361630567,\n" +
            "        \"isSubscribe\":0,\n" +
            "        \"parentUserId\":\"3dd44c2c8253bda1301eb183cce1b17e\",\n" +
            "        \"relation\":\"妈妈\",\n" +
            "        \"studentId\":659\n" +
            "    },\n" +
            "    {\n" +
            "        \"corpId\":\"ww52e65672fab7c894\",\n" +
            "        \"createTime\":1629361630568,\n" +
            "        \"isSubscribe\":0,\n" +
            "        \"parentUserId\":\"c222abe2b1c0f416480130639addb423\",\n" +
            "        \"relation\":\"妈妈\",\n" +
            "        \"studentId\":660\n" +
            "    },\n" +
            "    {\n" +
            "        \"corpId\":\"ww52e65672fab7c894\",\n" +
            "        \"createTime\":1629361630568,\n" +
            "        \"isSubscribe\":0,\n" +
            "        \"parentUserId\":\"3dd44c2c8253bda1301eb183cce1b17e\",\n" +
            "        \"relation\":\"妈妈\",\n" +
            "        \"studentId\":661\n" +
            "    },\n" +
            "    {\n" +
            "        \"corpId\":\"ww52e65672fab7c894\",\n" +
            "        \"createTime\":1629361630568,\n" +
            "        \"isSubscribe\":0,\n" +
            "        \"parentUserId\":\"e7963afc7345ffa0609947cb29677aa1\",\n" +
            "        \"relation\":\"爸爸\",\n" +
            "        \"studentId\":661\n" +
            "    },\n" +
            "    {\n" +
            "        \"corpId\":\"ww52e65672fab7c894\",\n" +
            "        \"createTime\":1629361630569,\n" +
            "        \"isSubscribe\":0,\n" +
            "        \"parentUserId\":\"3dd44c2c8253bda1301eb183cce1b17e\",\n" +
            "        \"relation\":\"妈妈\",\n" +
            "        \"studentId\":662\n" +
            "    },\n" +
            "    {\n" +
            "        \"corpId\":\"ww52e65672fab7c894\",\n" +
            "        \"createTime\":1629361630569,\n" +
            "        \"isSubscribe\":0,\n" +
            "        \"parentUserId\":\"3dd44c2c8253bda1301eb183cce1b17e\",\n" +
            "        \"relation\":\"妈妈\",\n" +
            "        \"studentId\":661\n" +
            "    },\n" +
            "    {\n" +
            "        \"corpId\":\"ww52e65672fab7c894\",\n" +
            "        \"createTime\":1629361630569,\n" +
            "        \"isSubscribe\":0,\n" +
            "        \"parentUserId\":\"e7963afc7345ffa0609947cb29677aa1\",\n" +
            "        \"relation\":\"爸爸\",\n" +
            "        \"studentId\":661\n" +
            "    }\n" +
            "]";

        List<test4> list = new ArrayList<>();
        List<test4> list2;
        JSONArray objects = JSON.parseArray(txt);
        log.info("原始数量={}", objects.size());
        for (int i=0; i<objects.size();i++){
            test4 test4 = JSON.parseObject(objects.get(i).toString(), test4.class);
            test4 t = new test4();
            BeanUtils.copyProperties(test4, t);
            list.add(t);
        }
        // 依据多个属性去除重复数据
        if (CollectionUtil.isNotEmpty(list)){
            list2 = list.stream().collect(
                    collectingAndThen(toCollection(() -> new TreeSet<>(Comparator.comparing(r-> r.getStudentId() + ";" + r.getParentUserId()))), ArrayList::new));
            log.info("处理后的数量={}", list2.size());
            System.out.println(JSON.toJSONString(list2));
        }

    }
}
