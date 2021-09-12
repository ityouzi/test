package org.example.dao;

import com.sample.SysRelation;
import java.util.List;

public interface SysRelationMapper {
    /**
     * delete by primary key
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(SysRelation record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(SysRelation record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    SysRelation selectByPrimaryKey(Integer id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(SysRelation record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(SysRelation record);

    int updateBatch(List<SysRelation> list);

    int updateBatchSelective(List<SysRelation> list);

//    int batchInsert(@Param("list") List<SysRelation> list);
}