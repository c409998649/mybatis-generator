package com.chen.mybatis.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * user
 * @author chenzhiying@zbj.com
 * @date 2019-01-11 09:54:44
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    // 用户编码
    private Integer userId;

    // 用户名称
    private String userName;

    // 用户电话
    private String userPhone;

    // 用户性别 1:男 2:女
    private Integer userSex;

    // 创建时间
    private Date userCreateTime;

    // 版本
    private Integer version;
}