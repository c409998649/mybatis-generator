package com.chen.mybatis.service;

import com.chen.mybatis.entity.User;

import java.util.List;

/**
 * @author chenzhiying@zbj.com
 * @title
 * @date 2019-01-11 上午10:01
 **/
public interface UserService {

    /**
     * 查询用户列表
     *
     * @param
     * @return
     * @title
     * @author chenzhiying@zbj.com
     * @date 19-1-11
     * @since
     **/
    List<User> queryUserList(String userName);
}
