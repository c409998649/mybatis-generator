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
     * @param userName
     * @return java.util.List<com.chen.mybatis.entity.User>
     * @title <功能描述>
     * @author chenzhiying@zbj.com
     * @date 19-1-11
     * @since <版本号>
     **/
    List<User> queryUserList(String userName);

    /**
     * 异步方法执行
     *
     * @return void
     * @title <功能描述>
     * @author chenzhiying@zbj.com
     * @date 19-1-11
     * @since <版本号>
     **/
    void asyncMessage();
}
