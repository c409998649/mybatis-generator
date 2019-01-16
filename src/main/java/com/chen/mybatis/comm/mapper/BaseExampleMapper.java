package com.chen.mybatis.comm.mapper;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * 通用mapper类
 *
 * @author 陈智颖
 * @title 通用mapper类
 * @date 2019-01-15 上午11:27
 **/
public interface BaseExampleMapper<R, E, ID extends Serializable> extends BaseMapper<R, ID>{

    long countByExample(E example);

    int deleteByExample(E example);

    List<R> selectByExampleWithBLOBs(E example);

    List<R> selectByExample(E example);

    int updateByExampleSelective(@Param("record") R record, @Param("example") E example);

    int updateByExampleWithBLOBs(@Param("record") R record, @Param("example") E example);

    int updateByExample(@Param("record") R record, @Param("example") E example);
}
