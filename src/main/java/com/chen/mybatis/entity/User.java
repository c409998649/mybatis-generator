package com.chen.mybatis.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * user
 * @author 陈智颖
 * @date 2019-01-16 11:15:04
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    // 姓名
    private String name;

    // 年龄
    private Integer age;

    // 创建时间
    private Date createTime;
}