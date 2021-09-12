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
public class SysNotice implements Serializable {
    /**
    * 主键
    */
    private Integer id;

    /**
    * 标题
    */
    private String title;

    /**
    * 类型
    */
    private Integer type;

    /**
    * 内容
    */
    private String content;

    /**
    * 创建时间
    */
    private Date createtime;

    /**
    * 创建人
    */
    private Integer creater;

    private static final long serialVersionUID = 1L;
}