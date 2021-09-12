package org.example;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lizhen created on 2021-08-20 17:55
 */
@Slf4j
public class 判断字符串是否依据指定字符开头 {


    public static void main(String[] args) {

        String a = "baidu  13246578941321320";

        String b = "ai 12345643121";

        if (a.startsWith("baidu") && b.startsWith("ai")){
            log.info("true");
        }else {
            log.info("false");
        }

    }
}
