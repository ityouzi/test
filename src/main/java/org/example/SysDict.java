package org.example;

import java.io.Serializable;
import lombok.Data;

@Data
public class SysDict implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 排序
     */
    private Integer num;

    /**
     * 父级字典
     */
    private Integer pid;

    /**
     * 名称
     */
    private String name;

    /**
     * 提示
     */
    private String tips;

    /**
     * 值
     */
    private String code;

    private static final long serialVersionUID = 1L;
}

