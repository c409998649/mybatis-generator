package com.chen.mybatis.controller;

import com.chen.mybatis.entity.User;
import com.chen.mybatis.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenzhiying@zbj.com
 * @title
 * @date 2019-01-11 上午10:05
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 查询用户权限
     *
     * @param userName 用户名称
     * @return
     * @title
     * @author chenzhiying@zbj.com
     * @date 19-1-11
     * @since
     **/
    @RequestMapping(value = "/userList", method = RequestMethod.POST)
    @ResponseBody
    public List<User> userList(String userName){
        return userService.queryUserList(userName);
    }
}
