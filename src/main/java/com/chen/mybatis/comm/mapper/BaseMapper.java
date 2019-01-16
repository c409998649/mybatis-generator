package com.chen.mybatis.comm.mapper;

import java.io.Serializable;

/**
 * 通用mapper类
 *
 * @author 陈智颖
 * @title 通用mapper类
 * @date 2019-01-15 上午11:22
 **/
public interface BaseMapper<R, ID extends Serializable> {

    int deleteByPrimaryKey(ID scId);

    int insert(R record);

    int insertSelective(R record);

    R selectByPrimaryKey(ID scId);

    int updateByPrimaryKeySelective(R record);

    int updateByPrimaryKeyWithBLOBs(R record);

    int updateByPrimaryKey(R record);
}
