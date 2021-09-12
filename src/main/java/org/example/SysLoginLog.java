package org.example;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class SysLoginLog implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 日志名称
     */
    private String logname;

    /**
     * 管理员id
     */
    private Integer userid;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 是否执行成功
     */
    private String succeed;

    /**
     * 具体消息
     */
    private String message;

    /**
     * 登录ip
     */
    private String ip;

    private static final long serialVersionUID = 1L;
}

