package com.chen.mybatis.service.impl;

import com.chen.mybatis.dao.mapper.UserMapper;
import com.chen.mybatis.entity.User;
import com.chen.mybatis.entity.UserExample;
import com.chen.mybatis.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenzhiying@zbj.com
 * @title
 * @date 2019-01-11 上午10:01
 **/
@Service
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
}
