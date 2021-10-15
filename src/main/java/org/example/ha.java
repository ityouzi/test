package org.example;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.*;
import java.util.*;

/**
 * @author lizhen created on 2021-10-11 11:20
 */
public class ha {

    /**
     * 转换为ArrayList，元素类型默认Object
     *
     * @param obj   被转换的值
     * @param clazz 集合中的对象
     * @author lizhen
     * @date 2021/10/11-14:25
     * @version 1.0
     * @return T
     */
    public static <T> List<T> castList(Object obj, Class<T> clazz){
        List<T> result = new ArrayList<T>();
        if (obj instanceof List<?>){
            for (Object o : (List<?>) obj){
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }

    /**
     * 使用RSA的加密算法对以上字符串StringA进行签名并进行Base64编码，得到sign值signValue
     * @param privateKey 商户私钥
     * @param content 内容
     * @param charset 字符集
     * @return base64 string
     */
    public static String rsaSign(PrivateKey privateKey, String content, String charset) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        java.security.Signature signet = java.security.Signature.getInstance("SHA1WithRSA");
        signet.initSign(privateKey);
        signet.update(content.getBytes(charset));
        byte[] signed = signet.sign();
        return new String(Base64.encodeBase64(signed), charset);
    }

    /**
     * 使用RSA的加密算法对以上字符串进行签名验证
     *
     * @param pubKey 商户公钥
     * @param content 内容
     * @param sign 签名
     * @param charset 字符集
     */
    public static boolean rsaCheckContent(PublicKey pubKey, String content, String sign, String charset) throws Exception {
        System.out.println("进入验证签名方法: content[" + content + "], sign[" + sign + "], charset[" + charset + "]");
        boolean bFlag = false;
        try {
            // 实例化签名 算法SHA1WithRSA
            java.security.Signature signetcheck = java.security.Signature.getInstance("SHA1WithRSA");
            // 初始化验证商户公钥
            signetcheck.initVerify(pubKey);
            // 更新字符集
            signetcheck.update(content.getBytes(charset));
            // 验证签名 base64
            if (signetcheck.verify(Base64.decodeBase64(sign.getBytes(charset)))) {
                bFlag = true;
            }
            System.out.println("进入验证签名方法: content[" + content + "], sign[" + sign + "], charset[" + charset + "], result[" + bFlag + "]");
        } catch (Exception e) {
            System.out.println("验证签名异常" + ": content[" + content + "], sign[" + sign + "], charset[" + charset + "]");
            throw new Exception("验证签名异常");
        } return bFlag;
    }

    /**
     *
     * 方法用途: 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序），并且生成参数串<br>
     * 实现步骤: <br>
     *
     * @param paraMap   要排序的Map对象
     * @param urlEncode   是否需要URLENCODE
     * @param keyToLower    是否需要将Key转换为全小写
     *            true:key转化成小写，false:不转化
     * @return string
     */
    public static String formatUrlMap(Map<String, String> paraMap, boolean urlEncode, boolean keyToLower) {
        String buff = "";
        try {
            List<Map.Entry<String, String>> infoIds = new ArrayList<>(paraMap.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            infoIds.sort(Map.Entry.comparingByKey());
            // 构造返回字符串键值对的格式
            StringBuilder buf = new StringBuilder();
            for (Map.Entry<String, String> item : infoIds)
            {
                if (StringUtils.isNotBlank(item.getKey()))
                {
                    String key = item.getKey();
                    String val = item.getValue();
                    if (urlEncode){
                        val = URLEncoder.encode(val, "utf-8");
                    }
                    if (keyToLower){
                        buf.append(key.toLowerCase()).append("=").append(val);
                    } else{
                        buf.append(key).append("=").append(val);
                    }
                    buf.append("&");
                }

            }
            buff = buf.toString();
            if (!buff.isEmpty()){
                buff = buff.substring(0, buff.length() - 1);
            }
        } catch (Exception e){
            return null;
        }
        return buff;
    }

    public static <T> Map<T, T> formatUrlMap2(Map<String, String> paraMap, boolean urlEncode, boolean keyToLower) throws UnsupportedEncodingException {
        List<Map.Entry<String, String>> infoIds = new ArrayList<>(paraMap.entrySet());
        // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
        infoIds.sort(Map.Entry.comparingByKey());
        Map<String, String> map = new HashMap<>(paraMap.size());

        infoIds.parallelStream().forEachOrdered(item -> {
            if (StringUtils.isNotBlank(item.getKey())){
                String key = item.getKey();
                String val = item.getValue();

                // 是否需要编码
                if (urlEncode){
                    try {
                        val = URLEncoder.encode(val, "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                // 是否将key全转小写
                if (keyToLower){
                    key = key.toLowerCase();
                }
                map.put(key, val);
            }
        });

        return (Map<T, T>) map;
    }

    /**
     * 遍历以及根据重新排序
     *
     * @param sortedParams
     * @return
     */
    private static String getSignContent(Map<String, String> sortedParams) {
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(sortedParams.keySet());
        Collections.sort(keys);
        int index = 0;
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = sortedParams.get(key);
            if (StringUtil.areNotEmpty(key, value)) {
                content.append((index == 0 ? "" : "&") + key + "=" + value);
                index++;
            }
        }
        return content.toString();
    }


    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
//        Student student1 = new Student(18, "张三", "一班", new Date());
//        Student student2 = new Student(18, null, "一班", new Date());
//        Student student3 = new Student(18, null, "一班", new Date());
//        Student student4 = new Student(18, "李四", "一班", new Date());
//
//        List<Student> studentList = Arrays.asList(student1, student2, student3, student4);
//        Map<String, Object> map = new HashMap<>();
//
//        map.put("aa", studentList);
//
//        List<Student> studentList1 = castList(map.get("aa"), Student.class);
//        List<Student> studentList2 = Convert.toList(Student.class, map.get("aa"));
//        System.out.println(studentList1);
//        System.out.println(studentList2);

        String a = "{\"biz_content\": \"123456\",\n" +
                "    \"charset\": \"UTF-8\",\n" +
                "    \"method\":\"ysepay.df.single.quick.accept\",\n" +
                "    \"notify_url\": \"http://javatest.ngrok.xiaomiqiu.cn/AsyncNotifyAction\",\n" +
                "    \"partner_id\":\"shanghu_test\",\n" +
                "    \"sign_type\": \"RSA\",\n" +
                "    \"timestamp\": \"2018-04-18 13:50:40\",\n" +
                "    \"version\": \"3.0\"\n" +
                "}";

        String sing = "{\"biz_content\":\n" +
                "    {\n" +
                "        \"out_trade_no\":\"2018041813504013504037403740\",\n" +
                "        \"business_code\":\"01000009\",\n" +
                "        \"currency\":\"CNY\",\n" +
                "        \"total_amount\":\"100\",\n" +
                "        \"subject\":\"银盛demo代付测试\",\n" +
                "        \"bank_name\":\"招商银行威盛支行\",\n" +
                "        \"bank_city\":\"深圳市\",\n" +
                "        \"bank_account_no\":\"900000782233747700\",\n" +
                "        \"bank_account_name\":\"姓名\",\n" +
                "        \"bank_account_type\":\"personal\",\n" +
                "        \"bank_card_type\":\"debit\"\n" +
                "        },\n" +
                "    \n" +
                "    \"charset\": \"UTF-8\",\n" +
                "    \"method\":\"ysepay.df.single.quick.accept\",\n" +
                "    \"notify_url\": \"http://javatest.ngrok.xiaomiqiu.cn/AsyncNotifyAction\",\n" +
                "    \"partner_id\":\"shanghu_test\",\n" +
                "    \"sign_type\": \"RSA\",\n" +
                "    \"timestamp\": \"2018-04-18 13:50:40\",\n" +
                "    \"version\": \"3.0\"\n" +
                "}\n";


        Map map = JSON.parseObject(sing, Map.class);
        System.out.println(map);



        // 排序
        Map map1 = JSON.parseObject(a, Map.class);
//        String s1 = formatUrlMap(map1, false, false);
//        System.out.println(s1);
//        Map map2 = JSON.parseObject(a, Map.class);
//        System.out.println(map2);
        Map map3 = formatUrlMap2(map1, false, false);
        System.out.println(JSON.toJSONString(map3));

        String signContent = getSignContent(JSON.parseObject(a, Map.class));
        System.out.println(signContent);


    }
}
