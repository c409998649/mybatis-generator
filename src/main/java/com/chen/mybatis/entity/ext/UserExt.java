package com.chen.mybatis.entity.ext;

import com.chen.mybatis.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户拓展
 *
 * @author chenzhiying@zbj.com
 * @title <一句话说明功能>
 * @date 2019-01-16 上午11:23
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class UserExt extends User implements Serializable {

}
