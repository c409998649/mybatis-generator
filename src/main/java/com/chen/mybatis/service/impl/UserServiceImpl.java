package com.chen.mybatis.service.impl;

import com.chen.mybatis.dao.mapper.UserMapper;
import com.chen.mybatis.entity.User;
import com.chen.mybatis.entity.UserExample;
import com.chen.mybatis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenzhiying@zbj.com
 * @title
 * @date 2019-01-11 上午10:01
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> queryUserList(String userName) {
        UserExample userExample = new UserExample();
        userExample.or().andUserNameLike('%'+userName+'%');
        List<User> users = userMapper.selectByExample(userExample);
        return users;
    }

    @Override
    @Async
    public void asyncMessage() {
        log.info("UserController执行方法2");
    }
}
