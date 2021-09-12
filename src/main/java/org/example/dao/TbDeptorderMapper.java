package org.example.dao;

import com.sample.TbDeptorder;

public interface TbDeptorderMapper {
    /**
     * delete by primary key
     * @param deptId primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer deptId);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(TbDeptorder record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(TbDeptorder record);

    /**
     * select by primary key
     * @param deptId primary key
     * @return object by primary key
     */
    TbDeptorder selectByPrimaryKey(Integer deptId);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(TbDeptorder record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(TbDeptorder record);
}