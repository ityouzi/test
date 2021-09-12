package com.sample;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CodeDbinfo implements Serializable {
    private Integer id;

    /**
     * 别名
     */
    private String name;

    /**
     * 数据库驱动
     */
    private String dbDriver;

    /**
     * 数据库地址
     */
    private String dbUrl;

    /**
     * 数据库账户
     */
    private String dbUserName;

    /**
     * 连接密码
     */
    private String dbPassword;

    /**
     * 数据库类型
     */
    private String dbType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}