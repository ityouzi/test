package com.sample;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysRelation implements Serializable {
    /**
    * 主键
    */
    private Integer id;

    /**
    * 菜单id
    */
    private Long menuid;

    /**
    * 角色id
    */
    private Integer roleid;

    private static final long serialVersionUID = 1L;
}