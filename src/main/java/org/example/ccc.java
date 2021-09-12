package org.example;

/**
 * @author lizhen created on 2021-09-09 16:09
 */
public enum ccc {

    /** 创建部门 */
    CREATE_DEPARTMENT ("创建部门"),

    /** 更新部门 */
    UPDATE_DEPARTMENT ("更新部门"),

    /** 删除部门 */
    DELETE_DEPARTMENT ("删除部门"),
    
    ;
    private final String desc;

    ccc(String desc){
        this.desc = desc;
    }
}
