package com.chen.mybatis.controller;

import com.chen.mybatis.entity.User;
import com.chen.mybatis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 陈智颖
 * @title
 * @date 2019-01-11 上午10:05
 **/
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 查询用户权限
     *
     * @param name
     * @return java.util.List<com.chen.mybatis.entity.User>
     * @title <一句话说明功能>
     * @author 陈智颖
     * @date 19-1-11
     * @since <版本号>
     **/
    @RequestMapping(value = "/userList")
    @ResponseBody
    public List<User> userList(String name){
        log.info("UserController执行方法1");
        List<User> users = userService.queryUserList(name);
        userService.asyncMessage();
        log.info("UserController执行方法3");
        return users;
    }
}
