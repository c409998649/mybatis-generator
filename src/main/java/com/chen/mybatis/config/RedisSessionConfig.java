package com.chen.mybatis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author 陈智颖
 * @create 2018-06-19 下午6:20
 **/

@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig {
}
