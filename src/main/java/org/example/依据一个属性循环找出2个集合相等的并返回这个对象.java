package org.example;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lizhen created on 2021-08-19 18:02
 */
@Slf4j
public class 依据一个属性循环找出2个集合相等的并返回这个对象 {



    public static void main(String[] args) {

        String txt1 = "[\n" +
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
                "        \"parentUserId\":\"e7963afc7345ffa0609947cb29677aa1\",\n" +
                "        \"relation\":\"爸爸\",\n" +
                "        \"studentId\":661\n" +
                "    }\n" +
                "]";

        String txt2 = "[\n" +
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

        // 数据1
        List<test4> test4List1 = new ArrayList<>();
        JSONArray tx1 = JSON.parseArray(txt1);
        // 数据2
        List<test4> test4List2 = new ArrayList<>();
        JSONArray tx2 = JSON.parseArray(txt2);

        for (int i = 0; i<tx1.size();i++){
            test4 test4 = JSON.parseObject(tx1.get(i).toString(), test4.class);
            test4List1.add(test4);
        }
        for (int i=0; i<tx2.size();i++){
            test4 test4 = JSON.parseObject(tx2.get(i).toString(), test4.class);
            test4List2.add(test4);
        }

        List<test4> orList = new ArrayList<>();
        int i = 0;
        for (test4 test4 : test4List1) {
            org.example.test4 test41 = test4List2.stream().filter(x ->
                    x.getParentUserId().equals(test4.getParentUserId())).findFirst().orElse(null);
            if (ObjectUtil.isEmpty(test41)){
                log.info("有一个null", i++);
            }
            if (ObjectUtil.isNotEmpty(test41)){
                orList.add(test41);
            }
        }
        System.out.println(JSON.toJSONString(orList));
        System.out.println(i);




    }
}
