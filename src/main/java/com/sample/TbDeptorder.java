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
public class TbDeptorder implements Serializable {
    private Integer deptId;

    /**
    * 单位名称
    */
    private String deptName;

    /**
    * 单位统一征信代码
    */
    private String deptCode;

    /**
    * 体检编号
    */
    private String hostpitalNum;

    /**
    * 体检机构
    */
    private String hostpitalCode;

    /**
    * 姓名
    */
    private String pName;

    /**
    * 性别
    */
    private String pSex;

    /**
    * 年龄
    */
    private Integer pAge;

    /**
    * 身份证ID
    */
    private String pIdcard;

    /**
    * 身份证照片
    */
    private String pPhoto;

    /**
    * 审核状态
    */
    private String status;

    /**
    * 手机号
    */
    private String pTelphone;

    /**
    * 登记日期
    */
    private String recordDate;

    /**
    * 创建时间
    */
    private String createTime;

    /**
    * 更新时间
    */
    private String updateTime;

    private static final long serialVersionUID = 1L;
}