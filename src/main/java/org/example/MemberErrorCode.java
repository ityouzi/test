package org.example;

/**
 * @author lizhen created on 2021-07-29 10:45
 */
public enum MemberErrorCode {

    E888("未绑定供应商，请联系管理员"),

    /**
     * 用户未认证
     */
    E999("用户未认证")

    ;

    private String describe;

    MemberErrorCode(String des) {
        this.describe = des;
    }

    /**
     * 获取异常码
     *
     * @return
     */
    public String code() {
        return this.name().replaceAll("E", "");
    }

    /**
     * 获取统计的错误消息
     *
     * @return
     */
    public String des() {
        return this.describe;
    }
}
