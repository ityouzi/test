package org.example;

import lombok.Data;

import java.io.Serializable;

/**
 * 部门model
 *
 * @author lizhen
 * @date 2021/4/28-17:50
 * @return
 */
@Data
public class SysDept implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 排序
     */
    private Integer num;

    /**
     * 父部门id
     */
    private Integer pid;

    /**
     * 父级ids
     */
    private String pids;

    /**
     * 简称
     */
    private String simplename;

    /**
     * 全称
     */
    private String fullname;

    /**
     * 提示
     */
    private String tips;

    /**
     * 版本（乐观锁保留字段）
     */
    private Integer version;

    private static final long serialVersionUID = 1L;
}

